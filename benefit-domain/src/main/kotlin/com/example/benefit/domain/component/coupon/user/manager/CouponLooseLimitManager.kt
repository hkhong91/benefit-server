package com.example.benefit.domain.component.coupon.user.manager

import com.example.benefit.domain.component.coupon.policy.document.Coupon
import com.example.benefit.domain.component.coupon.policy.data.CouponLimitLevel
import com.example.benefit.domain.component.coupon.policy.data.CouponIssueStatus
import com.example.benefit.domain.component.coupon.policy.infrastructure.CouponRepository
import com.example.benefit.domain.component.coupon.user.infrastructure.UserCouponRepository
import org.springframework.stereotype.Component

@Component
class CouponLooseLimitManager(
    private val userCouponRepository: UserCouponRepository,
    private val couponRepository: CouponRepository,
) : CouponLimitManager() {
    override val level = CouponLimitLevel.LOOSE

    override fun verify(userId: String, coupon: Coupon): CouponIssueStatus {
        val couponId = coupon.id
        val issue = coupon.issue
        val totalCount = userCouponRepository.countByCouponId(couponId)
        if (issue.isExhausted(totalCount)) {
            return CouponIssueStatus.EXHAUSTED
        }
        val issued = userCouponRepository.existsByUserIdAndCouponId(userId, couponId)
        if (!issued) {
            return CouponIssueStatus.DUPLICATED
        }
        return CouponIssueStatus.PASSED
    }

    override fun exhaust(userId: String, coupon: Coupon): Boolean {
        val exhaustedCoupon = couponRepository.save(coupon.exhaust())
        return exhaustedCoupon.issue.exhausted
    }
}