package com.example.data.remote.datasource

import com.example.domain.entity.signup.SignUpEntity

interface RemoteSignUpDataSource {
    suspend fun signUp(signUpEntity: SignUpEntity)
}