package com.example.benefit.domain.component.coupon.policy.infrastructure

import com.example.benefit.domain.component.coupon.policy.document.CouponGroup
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CouponGroupRepository : MongoRepository<CouponGroup, String> {
    fun findByCode(code: String): Optional<CouponGroup>

    fun findAllByCouponsId(couponId: String): List<CouponGroup>
}