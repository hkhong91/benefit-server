package com.example.benefit.domain.component.coupon.policy.document

import java.time.Duration
import java.time.Instant

data class CouponUsePeriod(
    val type: CouponUsePeriodType,
    val startAt: Instant? = null,
    val endAt: Instant? = null,
    val days: Long? = null,
) {
    fun getExpiredAt(now: Instant): Instant = when (this.type) {
        CouponUsePeriodType.FROM_TO -> this.endAt!!
        CouponUsePeriodType.WITHIN_DAYS -> now.plus(Duration.ofDays(this.days!!))
    }
}
