package com.example.data.repository

import com.example.data.remote.datasource.RemoteSignUpDataSource
import com.example.domain.entity.signup.SignUpEntity
import com.example.domain.respository.SignUpRepository
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val remoteSignUpDataSource: RemoteSignUpDataSource
): SignUpRepository {
    override suspend fun signUp(signUpEntity: SignUpEntity) = remoteSignUpDataSource.signUp(signUpEntity)
}