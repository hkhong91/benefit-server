package com.example.benefit.domain.component.coupon.user.manager

import com.example.benefit.domain.component.coupon.policy.document.Coupon
import com.example.benefit.domain.component.coupon.policy.data.CouponLimitLevel
import com.example.benefit.domain.component.coupon.policy.data.CouponIssueStatus
import com.example.benefit.domain.component.coupon.policy.infrastructure.CouponRepository
import com.example.benefit.domain.configuration.redis.RedisKey
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class CouponTransactionalLimitManager(
    private val stringRedisTemplate: StringRedisTemplate,
    private val couponRepository: CouponRepository,
) : CouponLimitManager() {
    override val level = CouponLimitLevel.TRANSACTIONAL

    override fun verify(userId: String, coupon: Coupon): CouponIssueStatus {
        val usersKey = RedisKey.couponUsers(coupon.id)

        val result = stringRedisTemplate.execute {
            val byteKey = usersKey.toByteArray()
            val byteValue = userId.toByteArray()
            it.multi()
            it.setCommands().sCard(byteKey)
            it.setCommands().sAdd(byteKey, byteValue)
            it.exec()
        }
        if (result?.size != 2) {
            return CouponIssueStatus.ERROR
        }

        val totalCount = result[0] as Long
        val addCount = result[1] as Long
        val issued = addCount > 0

        if (coupon.issue.isExhausted(totalCount)) {
            return CouponIssueStatus.EXHAUSTED
        }
        if (!issued) {
            return CouponIssueStatus.DUPLICATED
        }
        return CouponIssueStatus.PASSED
    }

    override fun exhaust(userId: String, coupon: Coupon): Boolean {
        val couponId = coupon.id
        val usersKey = RedisKey.couponUsers(couponId)
        val lockedKey = RedisKey.couponLocked(couponId)

        stringRedisTemplate.opsForSet()
            .remove(usersKey, userId)

        val locked = stringRedisTemplate.opsForValue()
            .setIfAbsent(lockedKey, "1", Duration.ofMinutes(10L)) ?: false
        if (locked) {
            val exhaustedCoupon = couponRepository.save(coupon.exhaust())
            stringRedisTemplate.expire(usersKey, Duration.ofDays(1L))
            return exhaustedCoupon.issue.exhausted
        }
        return false
    }
}