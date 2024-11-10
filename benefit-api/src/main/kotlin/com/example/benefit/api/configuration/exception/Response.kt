package com.example.benefit.api.configuration.exception

sealed class Response(
    val success: Boolean,
) {
    data class Success<T>(
        val body: T,
    ) : Response(true)

    data class Error(
        val code: ErrorCode,
        val message: String,
    ) : Response(false) {
        constructor(code: ErrorCode) : this(
            code = code,
            message = code.message,
        )
    }
}
