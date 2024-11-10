package com.example.benefit.domain.component.point.policy.data

enum class PointStatus {
    READY,
    ACTIVE,
    INACTIVE,
    ;

    fun isActive() = this == ACTIVE
}