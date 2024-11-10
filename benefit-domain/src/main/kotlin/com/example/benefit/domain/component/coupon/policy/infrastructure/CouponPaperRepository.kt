package com.example.benefit.domain.component.coupon.policy.infrastructure

import com.example.benefit.domain.component.coupon.policy.document.CouponPaper
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CouponPaperRepository : MongoRepository<CouponPaper, String> {
    fun findByCode(code: String): Optional<CouponPaper>
}