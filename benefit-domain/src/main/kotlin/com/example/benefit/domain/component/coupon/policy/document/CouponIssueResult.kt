package com.example.benefit.domain.component.coupon.policy.document

import com.example.benefit.domain.component.coupon.policy.data.CouponIssueStatus
import com.example.benefit.domain.component.coupon.user.document.UserCoupon

sealed class CouponIssueResult(
    open val status: CouponIssueStatus
) {
    data class Passed(
        val userCoupon: UserCoupon,
    ) : CouponIssueResult(CouponIssueStatus.PASSED)

    data class Failed(
        override val status: CouponIssueStatus,
    ) : CouponIssueResult(status)
}