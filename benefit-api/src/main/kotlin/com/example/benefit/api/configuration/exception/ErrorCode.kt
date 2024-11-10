package com.example.benefit.api.configuration.exception

enum class ErrorCode(
    val message: String,
) {
    E0000("서버 오류"),
    E0001("쿠폰 없음"),
    E0002("사용자 쿠폰 없음"),
    E0003("사용한 쿠폰 삭제 불가"),
    E0004("쿠폰 소진"),
    E0005("쿠폰 이미 발급 받음"),
    E0006("페이퍼 코드 없음"),
    E0007("만료된 쿠폰"),
    E0008("검증 로직 오류"),
    E0009("검증 로직 없음"),
    E0010("쿠폰 그룹 없음"),
    E0011("사용할 수 없는 코드"),
}