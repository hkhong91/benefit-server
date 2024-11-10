package com.example.benefit.api.component.point.policy.service

import com.example.benefit.api.component.point.policy.model.PointRequest
import com.example.benefit.api.component.point.policy.model.PointResponse
import com.example.benefit.api.configuration.exception.ApiException
import com.example.benefit.api.configuration.exception.ErrorCode
import com.example.benefit.domain.component.point.policy.document.PointLog
import com.example.benefit.domain.component.point.policy.infrastructure.PointLogRepository
import com.example.benefit.domain.component.point.policy.infrastructure.PointRepository
import org.springframework.stereotype.Service

@Service
class PointService(
    private val pointRepository: PointRepository,
    private val pointLogRepository: PointLogRepository,
) {
    fun findAll(): List<PointResponse> {
        return pointRepository.findAll()
            .map { PointResponse.of(it) }
    }

    fun findById(pointId: String): PointResponse {
        val point = pointRepository.findById(pointId)
            .orElseThrow { ApiException.of(ErrorCode.E0014) }
        return PointResponse.of(point)
    }

    fun findByCode(pointCode: String): PointResponse {
        val point = pointRepository.findByCode(pointCode)
            .orElseThrow { ApiException.of(ErrorCode.E0014) }
        return PointResponse.of(point)
    }

    fun create(request: PointRequest): PointResponse {
        val point = pointRepository.insert(request.createDocument())
        pointLogRepository.save(PointLog.of(point))
        return PointResponse.of(point)
    }

    fun modify(pointId: String, request: PointRequest): PointResponse {
        val point = pointRepository.findById(pointId)
            .orElseThrow { ApiException.of(ErrorCode.E0014) }
        val modifiedPoint = pointRepository.save(request.modifyDocument(point))
        pointLogRepository.save(PointLog.of(modifiedPoint))
        return PointResponse.of(modifiedPoint)
    }
}