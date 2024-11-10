package com.example.benefit.domain.component.coupon.policy.document

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "coupon_log")
data class CouponLog(
    val couponId: String,

    val title: String,

    val description: String,

    val status: CouponStatus,

    val benefit: CouponBenefit,

    val issue: CouponIssue,

    val usePeriod: CouponUsePeriod,

    val memo: String,
) {
    @Id
    lateinit var id: String

    @CreatedDate
    lateinit var createdAt: Instant

    companion object {
        fun of(coupon: Coupon) = CouponLog(
            couponId = coupon.id,
            title = coupon.title,
            description = coupon.description,
            status = coupon.status,
            benefit = coupon.benefit,
            issue = coupon.issue,
            usePeriod = coupon.usePeriod,
            memo = coupon.memo,
        )
    }
}
