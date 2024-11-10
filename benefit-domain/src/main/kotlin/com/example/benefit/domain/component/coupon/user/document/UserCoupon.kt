package com.example.benefit.domain.component.coupon.user.document

import com.example.benefit.domain.component.coupon.policy.document.Coupon
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "user_coupon")
data class UserCoupon(
    val title: String,

    val description: String,

    val userId: String,

    val couponId: String,

    var isUsed: Boolean,

    val expiredAt: Instant,
) {
    var usedAt: Instant? = null

    var canceledAt: Instant? = null

    @Id
    lateinit var id: String

    @CreatedDate
    lateinit var createdAt: Instant

    @LastModifiedDate
    lateinit var modifiedAt: Instant

    fun isUsable(at: Instant) = !this.isUsed && !this.expiredAt.isBefore(at)

    companion object {
        fun issue(userId: String, coupon: Coupon, now: Instant) = UserCoupon(
            title = coupon.title,
            description = coupon.description,
            userId = userId,
            couponId = coupon.id,
            isUsed = false,
            expiredAt = coupon.usePeriod.getExpiredAt(now)
        )
    }
}
