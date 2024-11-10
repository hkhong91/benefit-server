package com.example.benefit.api.component.coupon.user.controller

import com.example.benefit.api.component.coupon.user.service.UserCouponService
import com.example.benefit.api.configuration.web.ApiHeader
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class UserCouponController(
    private val userCouponService: UserCouponService,
) {
    @GetMapping("/user-coupons")
    fun findAll(
        @RequestHeader(ApiHeader.USER_ID) userId: String,
    ) = userCouponService.findAll(userId)

    @GetMapping("/user-coupons/{userCouponId}/detail")
    fun findDetail(
        @RequestHeader(ApiHeader.USER_ID) userId: String,
        @PathVariable("userCouponId") userCouponId: String,
    ) = userCouponService.findDetail(userId, userCouponId)
}