package com.example.benefit.api.component.coupon.user.controller

import com.example.benefit.api.component.coupon.user.service.UserCouponIssueService
import com.example.benefit.api.configuration.web.ApiHeader
import org.springframework.web.bind.annotation.*

@RestController
class UserCouponIssueController(
    private val userCouponIssueService: UserCouponIssueService,
) {
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
}