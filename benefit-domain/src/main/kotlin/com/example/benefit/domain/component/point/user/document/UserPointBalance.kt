package com.example.benefit.domain.component.point.user.document

import com.example.benefit.domain.component.point.policy.document.Point
import java.time.Duration
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
    fun isExpiringTomorrow(at: Instant) = expiredAt.isBefore(at.plus(Duration.ofDays(1L)))

    fun isExpiringSoon(at: Instant) = expiredAt.isBefore(at.plus(Duration.ofDays(7L)))

    fun isExpired(at: Instant) = expiredAt.isBefore(at)

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
