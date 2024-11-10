package com.example.benefit.api.component.coupon.policy.controller

import com.example.benefit.api.component.coupon.policy.model.CouponRequest
import com.example.benefit.api.component.coupon.policy.service.CouponService
import org.springframework.web.bind.annotation.*

@RestController
class CouponController(
    private val couponService: CouponService,
) {
    @GetMapping("/coupons")
    fun findAll() = couponService.findAll()

    @GetMapping("/coupons/{couponId}")
    fun findById(
        @PathVariable("couponId") couponId: String,
    ) = couponService.findById(couponId)

    @GetMapping("/coupons/{issueCode}/by-code")
    fun findByIssueCode(
        @PathVariable("issueCode") issueCode: String,
    ) = couponService.findByIssueCode(issueCode)

    @PostMapping("/coupons")
    fun create(
        @RequestBody request: CouponRequest,
    ) = couponService.create(request)

    @PutMapping("/coupons/{couponId}")
    fun modify(
        @PathVariable("couponId") couponId: String,
        @RequestBody request: CouponRequest,
    ) = couponService.modify(couponId, request)
}