package com.example.benefit.api.component.coupon.policy.service

import com.example.benefit.api.component.coupon.policy.model.CouponRequest
import com.example.benefit.api.component.coupon.policy.model.CouponResponse
import com.example.benefit.api.configuration.exception.ApiException
import com.example.benefit.api.configuration.exception.ErrorCode
import com.example.benefit.domain.component.coupon.policy.infrastructure.CouponRepository
import com.example.benefit.domain.component.coupon.policy.manager.CouponDataManager
import org.springframework.stereotype.Service

@Service
class CouponService(
    private val couponRepository: CouponRepository,
    private val couponDataManager: CouponDataManager,
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

    fun create(request: CouponRequest): CouponResponse {
        val coupon = couponRepository.insert(request.createDocument())
        couponDataManager.handleAfterCreate(coupon)
        return CouponResponse.of(coupon)
    }

    fun modify(couponId: String, request: CouponRequest): CouponResponse {
        val coupon = couponRepository.findById(couponId)
            .orElseThrow { ApiException.of(ErrorCode.E0001) }
        val modifiedCoupon = couponRepository.save(request.updateDocument(coupon))
        couponDataManager.handleAfterModify(modifiedCoupon)
        return CouponResponse.of(modifiedCoupon)
    }
}