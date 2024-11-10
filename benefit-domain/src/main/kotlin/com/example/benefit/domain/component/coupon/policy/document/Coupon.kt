package com.example.benefit.domain.component.coupon.policy.document

import com.example.benefit.domain.component.coupon.policy.data.CouponStatus
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "coupon")
data class Coupon(
    var title: String,

    var description: String,

    var status: CouponStatus,

    var benefit: CouponBenefit,

    var issue: CouponIssue,

    var usePeriod: CouponUsePeriod,

    var voucherRestrictions: List<CouponVoucherRestriction>,

    var memo: String,
) {
    @Id
    lateinit var id: String

    @CreatedDate
    lateinit var createdAt: Instant

    @LastModifiedDate
    lateinit var modifiedAt: Instant

    fun exhaust(): Coupon {
        issue.exhausted = true
        return this
    }

    fun modify(coupon: Coupon): Coupon {
        title = coupon.title
        description = coupon.description
        status = coupon.status
        benefit = coupon.benefit
        issue = coupon.issue
        usePeriod = coupon.usePeriod
        voucherRestrictions = coupon.voucherRestrictions
        memo = coupon.memo
        return coupon
    }
}
