package com.example.benefit.domain.component.point.user.document

import com.example.benefit.domain.component.point.user.data.UserPointTxType
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("user_point_tx")
data class UserPointTx(
    val userId: String,

    val type: UserPointTxType,

    val amount: Long,
) {
    @Id
    lateinit var id: String

    @CreatedDate
    lateinit var createdAt: Instant
}
