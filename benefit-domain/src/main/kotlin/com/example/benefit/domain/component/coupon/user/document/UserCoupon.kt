package com.example.benefit.domain.component.coupon.user.document

import com.example.benefit.domain.component.coupon.policy.document.Coupon
import com.example.benefit.domain.component.coupon.policy.document.CouponBenefit
import com.example.benefit.domain.component.coupon.policy.document.CouponVoucherRestriction
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "user_coupon")
data class UserCoupon(
    val userId: String,

    val couponId: String,

    var title: String,

    var description: String,

    var expiredAt: Instant,

    var benefit: CouponBenefit,

    var voucherRestrictions: List<CouponVoucherRestriction>,
) {
    var isUsed: Boolean = false

    var discountAmount: Long? = null

    var usedAt: Instant? = null

    var canceledAt: Instant? = null

    @Id
    lateinit var id: String

    @CreatedDate
    lateinit var createdAt: Instant

    @LastModifiedDate
    lateinit var modifiedAt: Instant

    fun isEqualsTo(coupon: Coupon, at: Instant): Boolean {
        return title == coupon.title
            && description == coupon.description
            && expiredAt == coupon.usePeriod.getExpiredAt(at)
    }

    fun modify(coupon: Coupon, at: Instant): UserCoupon {
        title = coupon.title
        description = coupon.description
        expiredAt = coupon.usePeriod.getExpiredAt(at)
        return this
    }

    fun isUsable(at: Instant) = !isUsed && !expiredAt.isBefore(at)

    companion object {
        fun issue(userId: String, coupon: Coupon, at: Instant) = UserCoupon(
            userId = userId,
            couponId = coupon.id,
            title = coupon.title,
            description = coupon.description,
            expiredAt = coupon.usePeriod.getExpiredAt(at),
            benefit = coupon.benefit,
            voucherRestrictions = coupon.voucherRestrictions,
        )
    }
}
