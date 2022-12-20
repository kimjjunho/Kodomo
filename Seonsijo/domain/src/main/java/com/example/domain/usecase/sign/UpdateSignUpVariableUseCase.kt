package com.example.domain.usecase.sign

import com.example.domain.entity.signup.SignUpEntity
import com.example.domain.respository.SignRepository
import com.example.domain.usecase.UseCase
import javax.inject.Inject

class UpdateSignUpVariableUseCase @Inject constructor(
    private val signRepository: SignRepository
): UseCase<SignUpEntity,Unit>(){
    override suspend fun execute(data: SignUpEntity) = signRepository.updateSignUpVariable(data)
}