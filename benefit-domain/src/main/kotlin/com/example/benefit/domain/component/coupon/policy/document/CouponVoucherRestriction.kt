package com.example.benefit.domain.component.coupon.policy.document

data class CouponVoucherRestriction(
    val affiliateId: String,
    val isAllProducts: Boolean,
    val productIds: Set<String>,
)
