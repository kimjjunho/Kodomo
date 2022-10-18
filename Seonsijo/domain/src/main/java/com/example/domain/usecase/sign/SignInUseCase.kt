package com.example.domain.usecase.sign

import com.example.domain.param.SignInParam
import com.example.domain.respository.SignRepository
import com.example.domain.usecase.UseCase
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val signUpRepository: SignRepository
): UseCase<Unit, SignInParam>(){
    override suspend fun execute(data: Unit): SignInParam = signUpRepository.autoSignIn()
}