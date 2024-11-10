package com.example.benefit.api.component.coupon.user.controller

import com.example.benefit.api.component.coupon.user.service.UserCouponUseService
import com.example.benefit.api.configuration.web.ApiHeader
import org.springframework.web.bind.annotation.*

@RestController
class UserCouponUseController(
    private val userCouponUseService: UserCouponUseService,
) {
    @PostMapping("/user-coupons/{userCouponId}/use")
    fun use(
        @RequestHeader(ApiHeader.USER_ID) userId: String,
        @PathVariable("userCouponId") userCouponId: String,
    ) = userCouponUseService.use(userId, userCouponId)

    @DeleteMapping("/user-coupons/{userCouponId}/use")
    fun cancelUse(
        @RequestHeader(ApiHeader.USER_ID) userId: String,
        @PathVariable("userCouponId") userCouponId: String,
    ) = userCouponUseService.cancelUse(userId, userCouponId)
}