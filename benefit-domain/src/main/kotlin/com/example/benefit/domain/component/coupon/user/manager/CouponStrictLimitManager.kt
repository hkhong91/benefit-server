package com.example.benefit.domain.component.coupon.user.manager

import com.example.benefit.domain.component.coupon.policy.document.Coupon
import com.example.benefit.domain.component.coupon.policy.document.CouponLimitLevel
import com.example.benefit.domain.component.coupon.policy.document.CouponIssueStatus
import com.example.benefit.domain.component.coupon.policy.infrastructure.CouponRepository
import com.example.benefit.domain.configuration.redis.RedisKey
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class CouponStrictLimitManager(
    private val stringRedisTemplate: StringRedisTemplate,
    private val couponRepository: CouponRepository,
) : CouponLimitManager() {
    override val level = CouponLimitLevel.STRICT

    override fun verify(userId: String, coupon: Coupon): CouponIssueStatus {
        val couponId = coupon.id
        val countKey = RedisKey.couponCount(couponId)
        val userKey = RedisKey.couponUser(couponId, userId)

        val totalCount = stringRedisTemplate.opsForValue().increment(countKey)
            ?: return CouponIssueStatus.ERROR
        if (coupon.issue.isExhausted(totalCount)) {
            return CouponIssueStatus.EXHAUSTED
        }

        val passed = stringRedisTemplate.opsForValue()
            .setIfAbsent(userKey, "1", Duration.ofMinutes(10L)) ?: false
        if (passed) {
            return CouponIssueStatus.PASSED
        } else {
            stringRedisTemplate.opsForValue().decrement(countKey)
            return CouponIssueStatus.DUPLICATED
        }
    }

    override fun exhaust(userId: String, coupon: Coupon): Boolean {
        val couponId = coupon.id
        val countKey = RedisKey.couponCount(couponId)
        val modifiedCoupon = couponRepository.save(coupon.exhaust())
        stringRedisTemplate.expire(countKey, Duration.ofDays(1L))
        return modifiedCoupon.issue.exhausted
    }
}