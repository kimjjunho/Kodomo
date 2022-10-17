package com.example.domain.respository

import com.example.domain.entity.signup.SignUpEntity

interface SignUpRepository {
    suspend fun signUp(signUpEntity: SignUpEntity)
}