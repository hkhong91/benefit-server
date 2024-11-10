package com.example.benefit.api.component.coupon.user.service

import com.example.benefit.api.component.coupon.user.model.UserCouponDetailResponse
import com.example.benefit.api.component.coupon.user.model.UserCouponResponse
import com.example.benefit.api.configuration.exception.ApiException
import com.example.benefit.api.configuration.exception.ErrorCode
import com.example.benefit.domain.component.coupon.policy.infrastructure.CouponRepository
import com.example.benefit.domain.component.coupon.user.infrastructure.UserCouponRepository
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class UserCouponService(
    private val userCouponRepository: UserCouponRepository,
    private val couponRepository: CouponRepository,
) {
    fun findAll(userId: String): List<UserCouponResponse> {
        val now = Instant.now()
        return userCouponRepository.findAllByUserIdAndExpiredAtBefore(userId, now)
            .filter { !it.isUsed }
            .map { UserCouponResponse.of(it) }
    }

    fun findDetail(userId: String, userCouponId: String): UserCouponDetailResponse {
        val userCoupon = userCouponRepository.findById(userCouponId)
            .orElseThrow { ApiException.of(ErrorCode.E0002) }
        val coupon = couponRepository.findById(userCoupon.couponId)
            .orElseThrow { ApiException.of(ErrorCode.E0001) }
        return UserCouponDetailResponse.of(userCoupon, coupon)
    }
}