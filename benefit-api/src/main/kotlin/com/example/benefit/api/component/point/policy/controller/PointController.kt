package com.example.benefit.api.component.point.policy.controller

import com.example.benefit.api.component.point.policy.model.PointRequest
import com.example.benefit.api.component.point.policy.service.PointService
import org.springframework.web.bind.annotation.*

@RestController
class PointController(
    private val pointService: PointService,
) {
    @GetMapping("/points")
    fun findAll() = pointService.findAll()

    @GetMapping("/points/{pointId}")
    fun findById(
        @PathVariable("pointId") pointId: String,
    ) = pointService.findById(pointId)

    @GetMapping("/points/{pointCode}/by-code")
    fun findByCode(
        @PathVariable("pointCode") pointCode: String,
    ) = pointService.findByCode(pointCode)

    @PostMapping("/points")
    fun create(
        @RequestBody request: PointRequest,
    ) = pointService.create(request)

    @PutMapping("/points/{pointId}")
    fun modify(
        @PathVariable("pointId") pointId: String,
        @RequestBody request: PointRequest,
    ) = pointService.modify(pointId, request)
}