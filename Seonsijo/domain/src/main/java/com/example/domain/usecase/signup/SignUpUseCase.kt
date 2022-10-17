package com.example.domain.usecase.signup

import com.example.domain.entity.signup.SignUpEntity
import com.example.domain.respository.SignUpRepository
import com.example.domain.usecase.UseCase
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
): UseCase<SignUpEntity, Unit>() {
    override suspend fun execute(data: SignUpEntity) = signUpRepository.signUp(data)
}