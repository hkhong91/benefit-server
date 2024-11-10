package com.example.benefit.domain.component.coupon.user.infrastructure

import com.example.benefit.domain.component.coupon.user.document.UserCouponDeleteLog
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserCouponDeleteLogRepository : MongoRepository<UserCouponDeleteLog, String>