package com.example.benefit.domain.component.coupon.user.manager

import com.example.benefit.domain.component.coupon.policy.document.Coupon
import com.example.benefit.domain.component.coupon.policy.document.CouponIssueResult
import com.example.benefit.domain.component.coupon.policy.data.CouponIssueStatus
import com.example.benefit.domain.component.coupon.user.document.UserCoupon
import com.example.benefit.domain.component.coupon.user.infrastructure.UserCouponRepository
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class CouponIssueManager(
    private val userCouponRepository: UserCouponRepository,
    private val couponLimitManagerFactory: CouponLimitManagerFactory,
) {

    fun issue(userId: String, coupon: Coupon, at: Instant): CouponIssueResult {
        val issue = coupon.issue
        if (!coupon.status.isActive()) return CouponIssueResult.Failed(CouponIssueStatus.ERROR)
        if (issue.exhausted) return CouponIssueResult.Failed(CouponIssueStatus.EXHAUSTED)
        if (issue.isEligible(at)) return CouponIssueResult.Failed(CouponIssueStatus.EXPIRED)

        val couponLimitManager = couponLimitManagerFactory.create(issue.level)
            ?: return CouponIssueResult.Failed(CouponIssueStatus.ERROR)
        return when (val status = couponLimitManager.limit(userId, coupon)) {
            CouponIssueStatus.PASSED -> {
                val userCoupon = userCouponRepository.insert(UserCoupon.issue(userId, coupon, at))
                CouponIssueResult.Passed(userCoupon)
            }

            else -> CouponIssueResult.Failed(status)
        }
    }
}