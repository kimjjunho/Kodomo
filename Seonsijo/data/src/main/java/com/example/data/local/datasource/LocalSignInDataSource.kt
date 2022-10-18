package com.example.data.local.datasource

import com.example.domain.entity.signup.SignUpEntity
import com.example.domain.param.SignInParam
import kotlinx.coroutines.flow.Flow

interface LocalSignInDataSource {

    suspend fun fetchSignInVariable(): SignInParam

    suspend fun saveSignInVariable(signUpEntity: SignUpEntity)
}