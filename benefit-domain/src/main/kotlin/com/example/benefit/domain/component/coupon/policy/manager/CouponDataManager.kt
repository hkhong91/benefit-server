package com.example.benefit.domain.component.coupon.policy.manager

import com.example.benefit.domain.component.coupon.policy.document.Coupon
import com.example.benefit.domain.component.coupon.policy.document.CouponLog
import com.example.benefit.domain.component.coupon.policy.infrastructure.CouponGroupRepository
import com.example.benefit.domain.component.coupon.policy.infrastructure.CouponLogRepository
import org.springframework.stereotype.Component

@Component
class CouponDataManager(
    private val couponLogRepository: CouponLogRepository,
    private val couponGroupRepository: CouponGroupRepository,
) {
    fun handleAfterCreate(coupon: Coupon) {
        couponLogRepository.save(CouponLog.of(coupon))
    }

    fun handleAfterModify(modifiedCoupon: Coupon) {
        val couponId = modifiedCoupon.id
        couponLogRepository.save(CouponLog.of(modifiedCoupon))

        for (group in couponGroupRepository.findAllByCouponsId(couponId)) {
            var isModified = false
            group.coupons
                .filter { it.id == couponId }
                .forEach {
                    isModified = true
                    it.modify(modifiedCoupon)
                }

            if (isModified) {
                couponGroupRepository.save(group)
            }
        }
    }
}