package com.example.benefit.domain.component.coupon.policy.document

import com.example.benefit.domain.component.coupon.policy.data.CouponLimitLevel
import java.time.Instant

data class CouponIssue(
    val code: String,
    val startAt: Instant,
    val endAt: Instant,
    val totalLimit: Long,
    val level: CouponLimitLevel,
    var exhausted: Boolean,
) {
    fun isEligible(at: Instant) = at in startAt..endAt

    fun isExhausted(totalCount: Long) = totalCount >= totalLimit
}
