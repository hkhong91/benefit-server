package com.example.benefit.api.component.coupon.user.service

import com.example.benefit.api.component.coupon.user.model.UserCouponResponse
import com.example.benefit.api.component.coupon.user.model.UserCouponUseRequest
import com.example.benefit.api.configuration.exception.ApiException
import com.example.benefit.api.configuration.exception.ErrorCode
import com.example.benefit.domain.component.coupon.user.infrastructure.UserCouponRepository
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class UserCouponUseService(
    private val userCouponRepository: UserCouponRepository,
) {
    fun findUsableAll(userId: String, request: UserCouponUseRequest): List<UserCouponResponse> {
        val now = Instant.now()
        val userCoupons = userCouponRepository.findAllByUserIdAndExpiredAtBefore(userId, now)
            .filter { !it.isUsed && request.validate(it.voucherRestrictions) }
        return UserCouponResponse.listOf(userCoupons)
    }

    fun use(userId: String, userCouponId: String, request: UserCouponUseRequest): UserCouponResponse {
        val now = Instant.now()
        val userCoupon = userCouponRepository.findById(userCouponId)
            .orElseThrow { ApiException.of(ErrorCode.E0002) }
        if (!request.validate(userCoupon.voucherRestrictions)) {
            throw ApiException.of(ErrorCode.E0013)
        }

        if (userCoupon.isUsable(now)) {
            userCoupon.isUsed = true
            userCoupon.discountAmount = userCoupon.benefit.discount(request.baseAmount)
            userCoupon.usedAt = now
            val modifiedUserCoupon = userCouponRepository.save(userCoupon)
            return UserCouponResponse.of(modifiedUserCoupon)
        } else {
            throw ApiException.of(ErrorCode.E0009)
        }
    }

    fun cancelUse(userId: String, userCouponId: String): UserCouponResponse {
        val now = Instant.now()
        val userCoupon = userCouponRepository.findById(userCouponId)
            .orElseThrow { ApiException.of(ErrorCode.E0002) }
        if (userCoupon.isUsed) {
            userCoupon.isUsed = false
            userCoupon.canceledAt = now
            val modifiedUserCoupon = userCouponRepository.save(userCoupon)
            return UserCouponResponse.of(modifiedUserCoupon)
        } else {
            throw ApiException.of(ErrorCode.E0012)
        }
    }
}