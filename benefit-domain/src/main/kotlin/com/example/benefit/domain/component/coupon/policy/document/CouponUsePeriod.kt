package com.example.benefit.domain.component.coupon.policy.document

import com.example.benefit.domain.component.coupon.policy.data.CouponUsePeriodType
import java.time.Duration
import java.time.Instant

data class CouponUsePeriod(
    val type: CouponUsePeriodType,
    val startAt: Instant? = null,
    val endAt: Instant? = null,
    val days: Long? = null,
) {
    fun getExpiredAt(at: Instant): Instant = when (type) {
        CouponUsePeriodType.FROM_TO -> endAt!!
        CouponUsePeriodType.WITHIN_DAYS -> at.plus(Duration.ofDays(days!!))
    }
}
