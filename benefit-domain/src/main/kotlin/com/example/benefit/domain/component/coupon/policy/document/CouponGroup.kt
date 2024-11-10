package com.example.benefit.domain.component.coupon.policy.document

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "coupon_group")
data class CouponGroup(
    var code: String,

    var coupons: List<Coupon>
) {
    @Id
    lateinit var id: String

    @CreatedDate
    lateinit var createdAt: Instant

    @LastModifiedDate
    lateinit var modifiedAt: Instant

    fun modify(group: CouponGroup): CouponGroup {
        code = group.code
        coupons = group.coupons
        return group
    }
}
