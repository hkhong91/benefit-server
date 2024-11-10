package com.example.benefit.domain.component.point.policy.infrastructure

import com.example.benefit.domain.component.point.policy.document.PointLog
import org.springframework.data.mongodb.repository.MongoRepository

interface PointLogRepository : MongoRepository<PointLog, String>