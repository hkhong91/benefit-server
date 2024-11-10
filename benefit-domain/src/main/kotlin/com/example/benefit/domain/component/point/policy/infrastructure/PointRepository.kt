package com.example.benefit.domain.component.point.policy.infrastructure

import com.example.benefit.domain.component.point.policy.document.Point
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface PointRepository : MongoRepository<Point, String> {
    fun findByCode(code: String): Optional<Point>
}