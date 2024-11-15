package com.example.benefit.domain.component.coupon.user.manager

import com.example.benefit.domain.component.coupon.policy.data.CouponIssueStatus
import com.example.benefit.domain.component.coupon.policy.data.CouponLimitLevel
import com.example.benefit.domain.component.coupon.policy.document.Coupon
import org.slf4j.LoggerFactory

abstract class CouponLimitManager {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    abstract val level: CouponLimitLevel

    protected abstract fun verify(userId: String, coupon: Coupon): CouponIssueStatus

    protected abstract fun exhaust(userId: String, coupon: Coupon): Boolean

    fun limit(userId: String, coupon: Coupon): CouponIssueStatus {
        val status = verify(userId, coupon)
        if (status == CouponIssueStatus.EXHAUSTED) {
            val exhausted = exhaust(userId, coupon)
            if (exhausted) {
                log.info("[쿠폰 소진] couponId: ${coupon.id}")
            }
        }
        return status
    }
}