package com.example.benefit.api.component.coupon.user.controller

import com.example.benefit.api.component.coupon.user.model.UserCouponUseRequest
import com.example.benefit.api.component.coupon.user.service.UserCouponIssueService
import com.example.benefit.api.component.coupon.user.service.UserCouponService
import com.example.benefit.api.component.coupon.user.service.UserCouponUseService
import com.example.benefit.api.configuration.web.ApiHeader
import org.springframework.web.bind.annotation.*

@RestController
class UserCouponController(
    private val userCouponService: UserCouponService,
    private val userCouponIssueService: UserCouponIssueService,
    private val userCouponUseService: UserCouponUseService,
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

    @PostMapping("/user-coupons/issue/by-id")
    fun issueById(
        @RequestHeader(ApiHeader.USER_ID) userId: String,
        @RequestParam("couponId") couponId: String,
    ) = userCouponIssueService.issueById(userId, couponId)

    @PostMapping("/user-coupons/issue/by-issue-code")
    fun issueByIssueCode(
        @RequestHeader(ApiHeader.USER_ID) userId: String,
        @RequestParam("issueCode") issueCode: String,
    ) = userCouponIssueService.issueByIssueCode(userId, issueCode)

    @PostMapping("/user-coupons/issue/by-paper-code")
    fun issueByPaperCode(
        @RequestHeader(ApiHeader.USER_ID) userId: String,
        @RequestParam("paperCode") paperCode: String,
    ) = userCouponIssueService.issueByPaperCode(userId, paperCode)

    @DeleteMapping("/user-coupons/{userCouponId}")
    fun delete(
        @RequestHeader(ApiHeader.USER_ID) userId: String,
        @PathVariable("userCouponId") userCouponId: String,
    ) = userCouponIssueService.delete(userId, userCouponId)

    @GetMapping("/user-coupons/usable")
    fun findUsableAll(
        @RequestHeader(ApiHeader.USER_ID) userId: String,
        request: UserCouponUseRequest,
    ) = userCouponUseService.findUsableAll(userId, request)

    @PostMapping("/user-coupons/{userCouponId}/use")
    fun use(
        @RequestHeader(ApiHeader.USER_ID) userId: String,
        @PathVariable("userCouponId") userCouponId: String,
        @RequestBody request: UserCouponUseRequest,
    ) = userCouponUseService.use(userId, userCouponId, request)

    @DeleteMapping("/user-coupons/{userCouponId}/use")
    fun cancelUse(
        @RequestHeader(ApiHeader.USER_ID) userId: String,
        @PathVariable("userCouponId") userCouponId: String,
    ) = userCouponUseService.cancelUse(userId, userCouponId)
}