package com.example.benefit.domain.component.coupon.user.manager

import com.example.benefit.domain.component.coupon.policy.data.CouponLimitLevel
import org.springframework.stereotype.Component

@Component
class CouponLimitManagerFactory(
    couponLimitManagers: List<CouponLimitManager>,
) {
    val managerMap = couponLimitManagers.associateBy { it.level }

    fun create(level: CouponLimitLevel) = this.managerMap[level]
}