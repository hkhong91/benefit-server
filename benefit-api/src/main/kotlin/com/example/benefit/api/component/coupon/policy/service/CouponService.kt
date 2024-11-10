package com.example.benefit.api.component.coupon.policy.service

import com.example.benefit.api.component.coupon.policy.model.CouponRequest
import com.example.benefit.api.component.coupon.policy.model.CouponResponse
import com.example.benefit.api.configuration.exception.ApiException
import com.example.benefit.api.configuration.exception.ErrorCode
import com.example.benefit.domain.component.coupon.policy.document.CouponLog
import com.example.benefit.domain.component.coupon.policy.infrastructure.CouponLogRepository
import com.example.benefit.domain.component.coupon.policy.infrastructure.CouponRepository
import com.example.benefit.domain.component.coupon.policy.manager.CouponDataManager
import org.springframework.stereotype.Service

@Service
class CouponService(
    private val couponRepository: CouponRepository,
    private val couponDataManager: CouponDataManager,
    private val couponLogRepository: CouponLogRepository,
) {
    fun findAll(): List<CouponResponse> {
        return couponRepository.findAll()
            .map { CouponResponse.of(it) }
    }

    fun findById(couponId: String): CouponResponse {
        val coupon = couponRepository.findById(couponId)
            .orElseThrow { ApiException.of(ErrorCode.E0001) }
        return CouponResponse.of(coupon)
    }

    fun findByIssueCode(issueCode: String): CouponResponse {
        val coupon = couponRepository.findByIssueCode(issueCode)
            .orElseThrow { ApiException.of(ErrorCode.E0001) }
        return CouponResponse.of(coupon)
    }

    fun create(request: CouponRequest): CouponResponse {
        val coupon = couponRepository.insert(request.createDocument())
        couponLogRepository.insert(CouponLog.of(coupon))
        return CouponResponse.of(coupon)
    }

    fun modify(couponId: String, request: CouponRequest): CouponResponse {
        val coupon = couponRepository.findById(couponId)
            .orElseThrow { ApiException.of(ErrorCode.E0001) }
        val modifiedCoupon = couponRepository.save(request.modifyDocument(coupon))
        couponLogRepository.insert(CouponLog.of(modifiedCoupon))

        if (modifiedCoupon.status.isActive()) {
            couponDataManager.handleAfterModify(modifiedCoupon)
        }
        return CouponResponse.of(modifiedCoupon)
    }
}