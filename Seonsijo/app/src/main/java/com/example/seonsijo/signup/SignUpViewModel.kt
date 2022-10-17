package com.example.seonsijo.signup

import com.example.domain.entity.signup.SignUpEntity
import com.example.domain.exception.BadRequestException
import com.example.domain.exception.NotFoundException
import com.example.domain.exception.ServerException
import com.example.domain.usecase.signup.SignUpUseCase
import com.example.seonsijo.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
): BaseViewModel<SignUpViewModel.Event>() {

    fun signUp(signUpEntity: SignUpEntity) = execute(
        job = { signUpUseCase.execute(signUpEntity) },
        onSuccess = { emitEvent(Event.Success) },
        onFailure = {
            when(it){
                is BadRequestException -> emitEvent(Event.BadRequest)
                is NotFoundException -> emitEvent(Event.NotFound)
                is ServerException -> emitEvent(Event.Server)
            }
        }
    )

    sealed class Event {
        object Success : Event()
        object BadRequest : Event()
        object NotFound : Event()
        object Server: Event()
    }
}