package com.example.benefit.domain.component.point.policy.document

import com.example.benefit.domain.component.point.policy.data.PointStatus
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "point_log")
data class PointLog(
    val pointId: String,

    val code: String,

    val title: String,

    val description: String,

    val status: PointStatus,

    val amount: Long,

    val memo: String,

    val usePeriod: PointUsePeriod,

    val isChangeableToCash: Boolean,

    val createdAt: Instant,

    val modifiedAt: Instant,
) {
    companion object {
        fun of(point: Point) = PointLog(
            pointId = point.id,
            code = point.code,
            title = point.title,
            description = point.description,
            status = point.status,
            amount = point.amount,
            memo = point.memo,
            usePeriod = point.usePeriod,
            isChangeableToCash = point.isChangeableToCash,
            createdAt = point.createdAt,
            modifiedAt = point.modifiedAt,
        )
    }
}
