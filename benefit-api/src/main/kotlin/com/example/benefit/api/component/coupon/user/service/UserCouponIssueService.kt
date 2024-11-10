package com.example.benefit.api.component.coupon.user.service

import com.example.benefit.api.component.coupon.user.model.UserCouponResponse
import com.example.benefit.api.configuration.exception.ApiException
import com.example.benefit.api.configuration.exception.ErrorCode
import com.example.benefit.domain.component.coupon.policy.infrastructure.CouponPaperRepository
import com.example.benefit.domain.component.coupon.policy.infrastructure.CouponRepository
import com.example.benefit.domain.component.coupon.user.document.UserCouponDeleteLog
import com.example.benefit.domain.component.coupon.user.infrastructure.UserCouponDeleteLogRepository
import com.example.benefit.domain.component.coupon.user.infrastructure.UserCouponRepository
import com.example.benefit.domain.component.coupon.user.manager.CouponIssueManager
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class UserCouponIssueService(
    private val userCouponRepository: UserCouponRepository,
    private val userCouponDeleteLogRepository: UserCouponDeleteLogRepository,
    private val couponRepository: CouponRepository,
    private val couponPaperRepository: CouponPaperRepository,
    private val couponIssueManager: CouponIssueManager,
) {
    fun issueByIssueCode(userId: String, issueCode: String): UserCouponResponse {
        val now = Instant.now()
        val coupon = couponRepository.findByIssueCode(issueCode)
            .orElseThrow { ApiException.of(ErrorCode.E0001) }
        val issueResult = couponIssueManager.issue(userId, coupon, now)
        return UserCouponResponse.validateOf(issueResult)
    }

    fun issueByPaperCode(userId: String, paperCode: String): UserCouponResponse {
        val now = Instant.now()
        val paper = couponPaperRepository.findByCode(paperCode)
            .orElseThrow { ApiException.of(ErrorCode.E0006) }
        if (paper.isEligible(now)) {
            throw ApiException.of(ErrorCode.E0011)
        }
        val coupon = couponRepository.findById(paper.couponId)
            .orElseThrow { ApiException.of(ErrorCode.E0001) }
        val issueResult = couponIssueManager.issue(userId, coupon, now)
        return UserCouponResponse.validateOf(issueResult)
            .also {
                paper.userCouponId = it.id
                couponPaperRepository.save(paper)
            }
    }

    fun delete(userId: String, userCouponId: String): UserCouponResponse {
        val userCoupon = userCouponRepository.findById(userCouponId)
            .orElseThrow { ApiException.of(ErrorCode.E0002) }
        if (userCoupon.isUsed) throw ApiException.of(ErrorCode.E0003)

        userCouponRepository.deleteById(userCouponId)
        userCouponDeleteLogRepository.save(UserCouponDeleteLog.of(userCoupon))
        return UserCouponResponse.of(userCoupon)
    }
}