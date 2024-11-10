package com.example.benefit.domain.component.point.user.infrastructure

import com.example.benefit.domain.component.point.user.document.UserPoint
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface UserPointRepository : MongoRepository<UserPoint, Long> {
    fun findByUserId(userId: String): Optional<UserPoint>
}