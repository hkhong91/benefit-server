package com.example.benefit.domain.configuration.redis

object RedisKey {
    fun couponUsers(couponId: String) = "benefit:coupon:${couponId}:users"
    fun couponUser(couponId: String, userId: String) = "benefit:coupon:${couponId}:users:${userId}"
    fun couponCount(couponId: String) = "benefit:coupon:${couponId}:count"
    fun couponLocked(couponId: String) = "benefit:coupon:${couponId}:locked"
}