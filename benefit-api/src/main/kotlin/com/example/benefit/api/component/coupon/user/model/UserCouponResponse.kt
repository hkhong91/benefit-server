package com.example.benefit.api.component.coupon.user.model

import com.example.benefit.api.configuration.exception.ApiException
import com.example.benefit.api.configuration.exception.ErrorCode
import com.example.benefit.domain.component.coupon.policy.document.CouponIssueResult
import com.example.benefit.domain.component.coupon.policy.document.CouponIssueStatus
import com.example.benefit.domain.component.coupon.user.document.UserCoupon
import java.time.Instant

data class UserCouponResponse(
    val id: String,
    val title: String,
    val description: String,
    val userId: String,
    val couponId: String,
    val isUsed: Boolean,
    val expiredAt: Instant,
) {
    companion object {
        fun of(userCoupon: UserCoupon) = UserCouponResponse(
            id = userCoupon.id,
            title = userCoupon.title,
            description = userCoupon.description,
            userId = userCoupon.userId,
            couponId = userCoupon.couponId,
            isUsed = userCoupon.isUsed,
            expiredAt = userCoupon.expiredAt,
        )

        fun validateOf(result: CouponIssueResult): UserCouponResponse {
            if (result is CouponIssueResult.Passed) {
                return of(result.userCoupon)
            }
            when (result.status) {
                CouponIssueStatus.EXHAUSTED -> throw ApiException.of(ErrorCode.E0004)
                CouponIssueStatus.EXPIRED -> throw ApiException.of(ErrorCode.E0007)
                CouponIssueStatus.DUPLICATED -> throw ApiException.of(ErrorCode.E0005)
                else -> throw ApiException.of(ErrorCode.E0008)
            }
        }
    }
}
