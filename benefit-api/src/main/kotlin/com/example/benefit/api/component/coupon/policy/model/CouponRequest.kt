package com.example.benefit.api.component.coupon.policy.model

import com.example.benefit.domain.component.coupon.policy.document.*

data class CouponRequest(
    val type: CouponType,
    val title: String,
    val description: String,
    val status: CouponStatus,
    val benefit: CouponBenefit,
    val issue: CouponIssue,
    val usePeriod: CouponUsePeriod,
    val memo: String,
) {
    fun createDocument() = Coupon(
        type = this.type,
        title = this.title,
        description = this.description,
        status = this.status,
        benefit = this.benefit,
        issue = this.issue,
        usePeriod = this.usePeriod,
        memo = this.memo,
    )

    fun updateDocument(coupon: Coupon): Coupon {
        return coupon.modify(createDocument())
    }
}
