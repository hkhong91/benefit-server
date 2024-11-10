package com.example.benefit.domain.component.coupon.policy.infrastructure

import com.example.benefit.domain.component.coupon.policy.document.Coupon
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CouponRepository : MongoRepository<Coupon, String> {
    fun findByIssueCode(code: String): Optional<Coupon>
}