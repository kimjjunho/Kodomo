package com.example.data.remote.datasource

import com.example.data.remote.api.SignUpAPI
import com.example.data.remote.request.toRequest
import com.example.domain.entity.signup.SignUpEntity
import javax.inject.Inject

class RemoteSignUpDataSourceImpl @Inject constructor(
    private val signUpAPI: SignUpAPI
): RemoteSignUpDataSource {
    override suspend fun signUp(signUpEntity: SignUpEntity) = signUpAPI.signUp(signUpEntity.toRequest())
}