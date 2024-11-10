package com.example.benefit.api.component.coupon.policy.model

import com.example.benefit.domain.component.coupon.policy.document.CouponGroup
import java.time.Instant

data class CouponGroupResponse(
    val id: String,
    val code: String,
    val coupons: List<CouponResponse>,
    val createdAt: Instant,
    val modifiedAt: Instant,
) {
    companion object {
        fun of(group: CouponGroup) = CouponGroupResponse(
            id = group.id,
            code = group.code,
            coupons = CouponResponse.listOf(group.coupons),
            createdAt = group.createdAt,
            modifiedAt = group.modifiedAt,
        )
    }
}
