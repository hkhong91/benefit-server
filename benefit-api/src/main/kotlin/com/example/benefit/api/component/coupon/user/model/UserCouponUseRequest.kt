package com.example.benefit.api.component.coupon.user.model

import com.example.benefit.domain.component.coupon.policy.document.CouponVoucherRestriction

data class UserCouponUseRequest(
    val affiliateId: String,
    val productId: String,
    val baseAmount: Long,
) {
    fun validate(restrictions: List<CouponVoucherRestriction>) =
        restrictions.any { restriction ->
            affiliateId == restriction.affiliateId &&
                (restriction.isAllProducts || restriction.productIds.contains(productId))
        }
}