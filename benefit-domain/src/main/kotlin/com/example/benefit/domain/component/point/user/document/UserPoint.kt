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

    var balances: List<UserPointBalance>,

    var amount: Long,
) {
    @Id
    lateinit var id: String

    @CreatedDate
    lateinit var createdAt: Instant

    @LastModifiedDate
    lateinit var modifiedAt: Instant

    fun earn(point: Point, at: Instant) {
        val remainingBalances = mutableListOf(UserPointBalance.of(point, at))
        var remainingAmount = point.amount
        for (balance in balances) {
            if (!balance.isExpired(at)) {
                remainingBalances.add(balance)
                remainingAmount += balance.remainingAmount
            } else {
                break
            }
        }
        balances = remainingBalances.sortedBy { it.expiredAt }
        amount = remainingAmount
    }

    companion object {
        fun of(userId: String, point: Point, at: Instant) = UserPoint(
            userId = userId,
            amount = point.amount,
            balances = listOf(UserPointBalance.of(point, at)),
        )
    }
}
