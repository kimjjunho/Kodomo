package com.example.seonsijo.signup

import com.example.domain.entity.signup.SignUpEntity
import com.example.domain.exception.BadRequestException
import com.example.domain.exception.NotFoundException
import com.example.domain.exception.ServerException
import com.example.domain.param.SignInParam
import com.example.domain.usecase.sign.SignInUseCase
import com.example.domain.usecase.sign.SignUpUseCase
import com.example.domain.usecase.sign.UpdateSignUpVariableUseCase
import com.example.seonsijo.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val updateSignUpVariableUseCase: UpdateSignUpVariableUseCase,
    private val signInUseCase: SignInUseCase
) : BaseViewModel<SignUpViewModel.Event>() {

    private val _signIn = MutableStateFlow(SignInParam(1, 1, null, 0))
    val signIn: StateFlow<SignInParam> = _signIn

    fun signUp(signUpEntity: SignUpEntity) = execute(
        job = { signUpUseCase.execute(signUpEntity) },
        onSuccess = { emitEvent(Event.Success) },
        onFailure = {
            when (it) {
                is BadRequestException -> emitEvent(Event.BadRequest)
                is NotFoundException -> emitEvent(Event.NotFound)
                is ServerException -> emitEvent(Event.Server)
            }
        }
    )

    fun updateSignUpVariable(signUpEntity: SignUpEntity) = execute(
        job = { updateSignUpVariableUseCase.execute(signUpEntity) },
        onSuccess = {},
        onFailure = {}
    )

    fun signIn() = execute(
        job = { signInUseCase.execute(Unit) },
        onSuccess = {param ->
            _signIn.tryEmit(param)
        },
        onFailure = { }
    )

    sealed class Event {
        object Success : Event()
        object BadRequest : Event()
        object NotFound : Event()
        object Server : Event()
    }
}