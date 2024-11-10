package com.example.benefit.domain.component.coupon.policy.data

enum class CouponStatus {
    READY,
    ACTIVE,
    INACTIVE,
    ;

    fun isActive() = this == ACTIVE
}
