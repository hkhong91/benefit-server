package com.example.benefit.domain.component.coupon.policy.document

import com.example.benefit.domain.component.coupon.policy.data.CouponBenefitType
import kotlin.math.round

data class CouponBenefit(
    val type: CouponBenefitType,
    val amount: Long?,
    val percentage: Float?,
    val maxAmount: Long?,
) {
    fun discount(baseAmount: Long) = when (type) {
        CouponBenefitType.AMOUNT -> {
            val amount = amount!!
            if (baseAmount >= amount) baseAmount - amount
            else 0
        }

        CouponBenefitType.PERCENTAGE -> {
            val percentage = percentage!!
            val maxAmount = maxAmount!!
            val discountAmount = round(baseAmount * percentage / 100).toInt()
            if (maxAmount >= discountAmount) {
                baseAmount - discountAmount
            } else {
                baseAmount - maxAmount
            }
        }
    }
}