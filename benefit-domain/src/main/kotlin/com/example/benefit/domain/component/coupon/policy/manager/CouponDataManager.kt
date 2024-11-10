package com.example.benefit.domain.component.coupon.policy.manager

import com.example.benefit.domain.component.coupon.policy.document.Coupon
import com.example.benefit.domain.component.coupon.policy.infrastructure.CouponGroupRepository
import com.example.benefit.domain.component.coupon.user.infrastructure.UserCouponRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class CouponDataManager(
    private val couponGroupRepository: CouponGroupRepository,
    private val userCouponRepository: UserCouponRepository,
) {
    @Async
    fun handleAfterModify(modifiedCoupon: Coupon) {
        val couponId = modifiedCoupon.id

        val now = Instant.now()
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

        for (userCoupon in userCouponRepository.findAllByCouponId(couponId)) {
            if (!userCoupon.isEqualsTo(modifiedCoupon, now)) {
                userCouponRepository.save(userCoupon.modify(modifiedCoupon, now))
            }
        }
    }
}