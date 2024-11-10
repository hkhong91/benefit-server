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
        title = title,
        description = description,
        status = status,
        benefit = benefit,
        issue = issue,
        usePeriod = usePeriod,
        voucherRestrictions = voucherRestrictions,
        memo = memo,
    )

    fun modifyDocument(coupon: Coupon): Coupon {
        return coupon.modify(createDocument())
    }
}
