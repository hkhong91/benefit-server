package com.example.benefit.domain.component.coupon.policy.document

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "coupon")
data class Coupon(
    var type: CouponType,

    var title: String,

    var description: String,

    var status: CouponStatus,

    var benefit: CouponBenefit,

    var issue: CouponIssue,

    var usePeriod: CouponUsePeriod,

    var memo: String,
) {
    @Id
    lateinit var id: String

    @CreatedDate
    lateinit var createdAt: Instant

    @LastModifiedDate
    lateinit var modifiedAt: Instant

    fun isEqualsTo(coupon: Coupon): Boolean {
        return this.title == coupon.title
            && this.description == coupon.description
    }

    fun exhaust(): Coupon {
        issue.exhausted = true
        return this
    }

    fun modify(coupon: Coupon): Coupon {
        this.type = coupon.type
        this.title = coupon.title
        this.description = coupon.description
        this.status = coupon.status
        this.benefit = coupon.benefit
        this.issue = coupon.issue
        this.usePeriod = coupon.usePeriod
        this.memo = coupon.memo
        return coupon
    }
}
