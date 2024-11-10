package com.example.benefit.domain.component.point.user.document

import com.example.benefit.domain.component.point.policy.document.Point
import java.time.Instant

data class UserPointBalance(
    val pointId: String,
    val title: String,
    val description: String,
    val amount: Long,
    var remainingAmount: Long,
    val expiredAt: Instant,
    val isChangeableToCash: Boolean,
) {
    companion object {
        fun of(point: Point, at: Instant) = UserPointBalance(
            pointId = point.id,
            title = point.title,
            description = point.description,
            amount = point.amount,
            remainingAmount = point.amount,
            expiredAt = point.usePeriod.getExpiredAt(at),
            isChangeableToCash = point.isChangeableToCash,
        )
    }
}
