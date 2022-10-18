package com.example.data.repository

import com.example.data.local.datasource.LocalSignInDataSource
import com.example.data.remote.datasource.RemoteSignUpDataSource
import com.example.domain.entity.signup.SignUpEntity
import com.example.domain.param.SignInParam
import com.example.domain.respository.SignRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignRepositoryImpl @Inject constructor(
    private val remoteSignUpDataSource: RemoteSignUpDataSource,
    private val localSignUpDataSource: LocalSignInDataSource
): SignRepository {
    override suspend fun signUp(signUpEntity: SignUpEntity){
        remoteSignUpDataSource.signUp(signUpEntity)
    }

    override suspend fun updateSignUpVariable(signUpEntity: SignUpEntity) =
        localSignUpDataSource.saveSignInVariable(signUpEntity)

    override suspend fun autoSignIn(): SignInParam =
        localSignUpDataSource.fetchSignInVariable()
}