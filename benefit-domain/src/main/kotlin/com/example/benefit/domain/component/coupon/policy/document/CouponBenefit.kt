package com.example.benefit.domain.component.coupon.policy.document

import com.example.benefit.domain.component.coupon.policy.data.CouponBenefitType
import kotlin.math.round

data class CouponBenefit(
    val type: CouponBenefitType,
    val amount: Long?,
    val percentage: Float?,
    val maxAmount: Long?,
) {
    fun discount(baseAmount: Long) = when (this.type) {
        CouponBenefitType.AMOUNT -> {
            val amount = this.amount!!
            if (baseAmount >= amount) baseAmount - amount
            else 0
        }

        CouponBenefitType.PERCENTAGE -> {
            val percentage = this.percentage!!
            val maxAmount = this.maxAmount!!
            val discountAmount = round(baseAmount * percentage / 100).toInt()
            if (maxAmount >= discountAmount) {
                baseAmount - discountAmount
            } else {
                baseAmount - maxAmount
            }
        }
    }
}