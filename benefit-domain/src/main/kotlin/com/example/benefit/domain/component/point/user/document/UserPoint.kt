package com.example.benefit.domain.component.point.user.document

import com.example.benefit.domain.component.point.policy.document.Point
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("user_point")
data class UserPoint(
    val userId: String,

    var remainingAmount: Long,

    val balances: MutableList<UserPointBalance>,
) {
    @Id
    lateinit var id: String

    @CreatedDate
    lateinit var createdAt: Instant

    @LastModifiedDate
    lateinit var modifiedAt: Instant

    companion object {
        fun of(userId: String, point: Point, at: Instant) = UserPoint(
            userId = userId,
            remainingAmount = point.amount,
            balances = mutableListOf(UserPointBalance.of(point, at)),
        )
    }
}
