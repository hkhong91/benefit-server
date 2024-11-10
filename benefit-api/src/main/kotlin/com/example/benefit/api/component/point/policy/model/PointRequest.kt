package com.example.benefit.api.component.point.policy.model

import com.example.benefit.domain.component.point.policy.data.PointStatus
import com.example.benefit.domain.component.point.policy.document.Point
import com.example.benefit.domain.component.point.policy.document.PointUsePeriod

data class PointRequest(
    val code: String,
    val title: String,
    val description: String,
    val status: PointStatus,
    val amount: Long,
    val memo: String,
    val usePeriod: PointUsePeriod,
    val isChangeableToCash: Boolean,
) {
    fun createDocument() = Point(
        code = code,
        title = title,
        description = description,
        status = status,
        amount = amount,
        memo = memo,
        usePeriod = usePeriod,
        isChangeableToCash = isChangeableToCash,
    )

    fun modifyDocument(point: Point) = point.modify(createDocument())
}
