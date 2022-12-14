package com.example.domain.respository

import com.example.domain.entity.signup.SignUpEntity
import com.example.domain.param.SignInParam

interface SignRepository {
    suspend fun signUp(signUpEntity: SignUpEntity)

    suspend fun updateSignUpVariable(signUpEntity: SignUpEntity)

    suspend fun autoSignIn(): SignInParam
}