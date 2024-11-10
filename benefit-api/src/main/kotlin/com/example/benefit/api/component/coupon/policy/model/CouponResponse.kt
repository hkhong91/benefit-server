package com.example.benefit.api.component.coupon.policy.model

import com.example.benefit.domain.component.coupon.policy.data.CouponStatus
import com.example.benefit.domain.component.coupon.policy.document.*
import java.time.Instant

data class CouponResponse(
    val id: String,
    val title: String,
    val description: String,
    val status: CouponStatus,
    val benefit: CouponBenefit,
    val issue: CouponIssue,
    val usePeriod: CouponUsePeriod,
    val voucherRestrictions: List<CouponVoucherRestriction>,
    val memo: String,
    val createdAt: Instant,
    val modifiedAt: Instant,
) {
    companion object {
        fun listOf(coupons: List<Coupon>) = coupons.map { of(it) }

        fun of(coupon: Coupon) = CouponResponse(
            id = coupon.id,
            title = coupon.title,
            description = coupon.description,
            status = coupon.status,
            benefit = coupon.benefit,
            issue = coupon.issue,
            usePeriod = coupon.usePeriod,
            voucherRestrictions = coupon.voucherRestrictions,
            memo = coupon.memo,
            createdAt = coupon.createdAt,
            modifiedAt = coupon.modifiedAt,
        )
    }
}
