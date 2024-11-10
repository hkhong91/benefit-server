package com.example.benefit.api.configuration.exception

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiExceptionHandler {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @ExceptionHandler(ApiException::class)
    fun handle(e: ApiException): ResponseEntity<Response> {
        return ResponseEntity.ok()
            .body(Response.Error(e.code))
    }

    @ExceptionHandler(Exception::class)
    fun handle(e: Exception): ResponseEntity<Response> {
        log.error(e.message, e)
        return ResponseEntity.ok()
            .body(Response.Error(ErrorCode.E0000))
    }
}