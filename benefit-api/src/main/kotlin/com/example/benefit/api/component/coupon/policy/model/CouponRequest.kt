package com.example.benefit.api.component.coupon.policy.model

import com.example.benefit.domain.component.coupon.policy.data.CouponStatus
import com.example.benefit.domain.component.coupon.policy.document.*

data class CouponRequest(
    val title: String,
    val description: String,
    val status: CouponStatus,
    val benefit: CouponBenefit,
    val issue: CouponIssue,
    val usePeriod: CouponUsePeriod,
    val voucherRestrictions: List<CouponVoucherRestriction>,
    val memo: String,
) {
    fun createDocument() = Coupon(
        title = this.title,
        description = this.description,
        status = this.status,
        benefit = this.benefit,
        issue = this.issue,
        usePeriod = this.usePeriod,
        voucherRestrictions = this.voucherRestrictions,
        memo = this.memo,
    )

    fun updateDocument(coupon: Coupon): Coupon {
        return coupon.modify(createDocument())
    }
}
