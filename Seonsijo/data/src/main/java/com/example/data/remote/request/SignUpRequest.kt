package com.example.data.remote.request

import com.example.domain.entity.signup.SignUpEntity

data class SignUpRequest(
    val grade: Int,
    val class_num: Int,
    val device_token: String?
)

fun SignUpEntity.toRequest() = SignUpRequest(
    grade = grade,
    class_num = class_num,
    device_token = device_token
)
