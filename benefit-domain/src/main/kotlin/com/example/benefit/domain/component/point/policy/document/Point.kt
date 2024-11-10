package com.example.benefit.domain.component.point.policy.document

import com.example.benefit.domain.component.point.policy.data.PointStatus
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "point")
data class Point(
    var code: String,

    var title: String,

    var description: String,

    var status: PointStatus,

    var amount: Long,

    var memo: String,

    var usePeriod: PointUsePeriod,

    var isChangeableToCash: Boolean,
) {
    @Id
    lateinit var id: String

    @CreatedDate
    lateinit var createdAt: Instant

    @LastModifiedDate
    lateinit var modifiedAt: Instant

    fun modify(point: Point): Point {
        code = point.code
        title = point.title
        description = point.description
        status = point.status
        amount = point.amount
        memo = point.memo
        usePeriod = point.usePeriod
        isChangeableToCash = point.isChangeableToCash
        return point
    }
}
