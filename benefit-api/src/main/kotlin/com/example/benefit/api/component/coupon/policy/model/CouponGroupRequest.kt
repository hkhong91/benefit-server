package com.example.benefit.api.component.coupon.policy.model

import com.example.benefit.domain.component.coupon.policy.document.Coupon
import com.example.benefit.domain.component.coupon.policy.document.CouponGroup

data class CouponGroupRequest(
    val code: String,
    val couponIds: Set<String>,
) {
    fun createDocument(coupons: List<Coupon>) = CouponGroup(
        code = code,
        coupons = coupons,
    )

    fun modifyDocument(group: CouponGroup, coupons: List<Coupon>): CouponGroup {
        return group.modify(createDocument(coupons))
    }
}
