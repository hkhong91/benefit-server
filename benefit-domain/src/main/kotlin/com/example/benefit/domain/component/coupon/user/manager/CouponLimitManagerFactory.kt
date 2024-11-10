package com.example.benefit.domain.component.coupon.user.manager

import com.example.benefit.domain.component.coupon.policy.data.CouponLimitLevel
import org.springframework.stereotype.Component

@Component
class CouponLimitManagerFactory(
    couponLimitManagers: List<CouponLimitManager>,
    private val couponStrictLimitManager: CouponStrictLimitManager,
) {
    val managerMap = couponLimitManagers.associateBy { it.level }

    fun create(level: CouponLimitLevel) = managerMap[level] ?: couponStrictLimitManager
}