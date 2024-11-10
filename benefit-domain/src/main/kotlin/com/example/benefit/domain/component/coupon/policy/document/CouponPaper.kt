package com.example.benefit.domain.component.coupon.policy.document

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "coupon_paper")
data class CouponPaper(
    val couponId: String,

    val code: String,

    val expiredAt: Instant,
) {
    var userCouponId: String? = null

    @Id
    lateinit var id: String

    @CreatedDate
    lateinit var createdAt: Instant

    fun isEligible(at: Instant) = this.userCouponId == null && !this.expiredAt.isBefore(at)
}