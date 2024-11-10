package com.example.benefit.api.component.coupon.user.model

import com.example.benefit.api.component.coupon.policy.model.CouponResponse
import com.example.benefit.domain.component.coupon.policy.document.Coupon
import com.example.benefit.domain.component.coupon.user.document.UserCoupon
import java.time.Instant

data class UserCouponDetailResponse(
    val id: String,
    val title: String,
    val description: String,
    val userId: String,
    val coupon: CouponResponse,
    val isUsed: Boolean,
    val expiredAt: Instant,
) {
    companion object {
        fun of(userCoupon: UserCoupon, coupon: Coupon) = UserCouponDetailResponse(
            id = userCoupon.id,
            title = userCoupon.title,
            description = userCoupon.description,
            userId = userCoupon.userId,
            coupon = CouponResponse.of(coupon),
            isUsed = userCoupon.isUsed,
            expiredAt = userCoupon.expiredAt,
        )
    }
}
