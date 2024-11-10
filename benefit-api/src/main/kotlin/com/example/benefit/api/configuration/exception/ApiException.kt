package com.example.benefit.api.configuration.exception

open class ApiException(
    open val code: ErrorCode,
    override val message: String,
) : RuntimeException() {
    companion object {
        fun of(code: ErrorCode) = ApiException(
            code = code,
            message = code.message,
        )
    }
}