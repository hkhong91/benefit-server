package com.example.benefit.api.component.coupon.policy.service

import com.example.benefit.api.component.coupon.policy.model.CouponGroupRequest
import com.example.benefit.api.component.coupon.policy.model.CouponGroupResponse
import com.example.benefit.api.configuration.exception.ApiException
import com.example.benefit.api.configuration.exception.ErrorCode
import com.example.benefit.domain.component.coupon.policy.infrastructure.CouponGroupRepository
import com.example.benefit.domain.component.coupon.policy.infrastructure.CouponRepository
import org.springframework.stereotype.Service

@Service
class CouponGroupService(
    private val couponGroupRepository: CouponGroupRepository,
    private val couponRepository: CouponRepository,
) {
    fun findAll(): List<CouponGroupResponse> {
        return couponGroupRepository.findAll()
            .map { CouponGroupResponse.of(it) }
    }

    fun findById(groupId: String): CouponGroupResponse {
        val group = couponGroupRepository.findById(groupId)
            .orElseThrow { ApiException.of(ErrorCode.E0010) }
        return CouponGroupResponse.of(group)
    }

    fun create(request: CouponGroupRequest): CouponGroupResponse {
        val coupons = couponRepository.findAllById(request.couponIds)
        val group = couponGroupRepository.insert(request.createDocument(coupons))
        return CouponGroupResponse.of(group)
    }

    fun modify(groupId: String, request: CouponGroupRequest): CouponGroupResponse {
        val group = couponGroupRepository.findById(groupId)
            .orElseThrow { ApiException.of(ErrorCode.E0010) }
        val coupons = couponRepository.findAllById(request.couponIds)
        val modifiedGroup = couponGroupRepository.save(request.modifyDocument(group, coupons))
        return CouponGroupResponse.of(modifiedGroup)
    }
}