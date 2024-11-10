package com.example.benefit.domain.component.point.policy.document

import com.example.benefit.domain.component.point.policy.data.PointUsePeriodType
import java.time.*

data class PointUsePeriod(
    val type: PointUsePeriodType,
    val startAt: Instant? = null,
    val endAt: Instant? = null,
    val days: Long? = null,
) {
    fun getExpiredAt(at: Instant): Instant = when (type) {
        PointUsePeriodType.ALWAYS -> getNotExpiredAt()
        PointUsePeriodType.FROM_TO -> endAt!!
        PointUsePeriodType.WITHIN_DAYS -> at.plus(Duration.ofDays(days!!))
    }

    private fun getNotExpiredAt() =
        LocalDateTime.of(LocalDate.of(9999, 12, 31), LocalTime.MAX)
            .toInstant(ZoneOffset.UTC)
}
