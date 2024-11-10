package com.example.benefit.domain.component.coupon.user.document

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "user_coupon_delete_log")
data class UserCouponDeleteLog(
    val userCouponId: String,

    val title: String,

    val description: String,

    val userId: String,

    val couponId: String,

    val isUsed: Boolean,

    val expiredAt: Instant,

    val usedAt: Instant?,

    val canceledAt: Instant?,
) {
    @Id
    lateinit var id: String

    @CreatedDate
    lateinit var createdAt: Instant

    companion object {
        fun of(userCoupon: UserCoupon) = UserCouponDeleteLog(
            userCouponId = userCoupon.id,
            title = userCoupon.title,
            description = userCoupon.description,
            userId = userCoupon.userId,
            couponId = userCoupon.couponId,
            isUsed = userCoupon.isUsed,
            expiredAt = userCoupon.expiredAt,
            usedAt = userCoupon.usedAt,
            canceledAt = userCoupon.canceledAt,
        )
    }
}
