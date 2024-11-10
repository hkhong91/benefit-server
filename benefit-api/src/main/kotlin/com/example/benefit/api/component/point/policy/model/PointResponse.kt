package com.example.benefit.api.component.point.policy.model

import com.example.benefit.domain.component.point.policy.data.PointStatus
import com.example.benefit.domain.component.point.policy.document.Point
import com.example.benefit.domain.component.point.policy.document.PointUsePeriod
import java.time.Instant

data class PointResponse(
    val id: String,
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
        fun of(point: Point) = PointResponse(
            id = point.id,
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
