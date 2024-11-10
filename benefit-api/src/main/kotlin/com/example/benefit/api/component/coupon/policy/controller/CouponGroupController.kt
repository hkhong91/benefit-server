package com.example.benefit.api.component.coupon.policy.controller

import com.example.benefit.api.component.coupon.policy.model.CouponGroupRequest
import com.example.benefit.api.component.coupon.policy.service.CouponGroupService
import org.springframework.web.bind.annotation.*

@RestController
class CouponGroupController(
    private val couponGroupService: CouponGroupService,
) {
    @GetMapping("/coupon-groups")
    fun findAll() = couponGroupService.findAll()

    @GetMapping("/coupon-groups/{groupId}")
    fun findById(
        @PathVariable("groupId") groupId: String,
    ) = couponGroupService.findById(groupId)

    @PostMapping("/coupon-groups")
    fun create(
        @RequestBody request: CouponGroupRequest,
    ) = couponGroupService.create(request)

    @PutMapping("/coupon-groups/{groupId}")
    fun modify(
        @PathVariable("groupId") groupId: String,
        @RequestBody request: CouponGroupRequest,
    ) = couponGroupService.modify(groupId, request)
}