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
        this.title = coupon.title
        this.description = coupon.description
        this.status = coupon.status
        this.benefit = coupon.benefit
        this.issue = coupon.issue
        this.usePeriod = coupon.usePeriod
        this.voucherRestrictions = coupon.voucherRestrictions
        this.memo = coupon.memo
        return coupon
    }
}
