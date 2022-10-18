package com.example.data.local.datasource

import com.example.data.local.preference.SignInPreference
import com.example.domain.entity.signup.SignUpEntity
import com.example.domain.param.SignInParam
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalSignInDataSourceImpl @Inject constructor(
    private val signInPreference: SignInPreference
): LocalSignInDataSource{
    override suspend fun fetchSignInVariable(): SignInParam =
        with(signInPreference){
            SignInParam(
                grade = fetchGradeNum(),
                class_num = fetchClassNum(),
                device_token = fetchDeviceToken()
            )
        }


    override suspend fun saveSignInVariable(signUpEntity: SignUpEntity) =
        with(signInPreference){
            saveGradeNum(signUpEntity.grade)
            saveClassNum(signUpEntity.class_num)
            saveDeviceToken(signUpEntity.device_token)
        }
}