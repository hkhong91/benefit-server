package com.example.benefit.domain.component.coupon.policy.infrastructure

import com.example.benefit.domain.component.coupon.policy.document.CouponLog
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CouponLogRepository : MongoRepository<CouponLog, String> {
}