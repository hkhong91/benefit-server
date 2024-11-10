package com.example.benefit.domain.component.coupon.user.infrastructure

import com.example.benefit.domain.component.coupon.user.document.UserCoupon
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
interface UserCouponRepository : MongoRepository<UserCoupon, String> {
    fun countByCouponId(couponId: String): Long

    fun existsByUserIdAndCouponId(userId: String, couponId: String): Boolean

    fun findAllByUserIdAndExpiredAtBefore(userId: String, expiredAt: Instant): List<UserCoupon>

    fun findAllByCouponIdAndExpiredAtBefore(couponId: String, expiredAt: Instant): List<UserCoupon>
}