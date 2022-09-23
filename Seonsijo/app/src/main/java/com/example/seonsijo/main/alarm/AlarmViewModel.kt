package com.example.seonsijo.main.alarm

import com.example.domain.entity.alarm.DeleteAlarmRequestEntity
import com.example.domain.entity.alarm.GetAlarmRequestEntity
import com.example.domain.exception.BadRequestException
import com.example.domain.exception.ForbiddenException
import com.example.domain.exception.NotFoundException
import com.example.domain.exception.ServerException
import com.example.domain.exception.UnauthorizedException
import com.example.domain.usecase.alarm.DeleteAlarmUseCase
import com.example.domain.usecase.alarm.GetAlarmUseCase
import com.example.seonsijo.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val getAlarmUseCase: GetAlarmUseCase,
    private val deleteAlarmUseCase: DeleteAlarmUseCase
): BaseViewModel<AlarmViewModel.Event>() {

    fun getAlarm(getAlarmRequestEntity: GetAlarmRequestEntity) = execute(
        job = { getAlarmUseCase.execute(getAlarmRequestEntity) },
        onSuccess = { emitEvent(Event.Success) },
        onFailure = {
            when(it){
                is BadRequestException -> emitEvent(Event.BadRequest)
                is UnauthorizedException -> emitEvent(Event.Unauthorized)
                is ForbiddenException -> emitEvent(Event.Forbidden)
                is NotFoundException -> emitEvent(Event.NotFound)
                is ServerException -> emitEvent(Event.Server)
            }
        }
    )

    fun deleteAlarm(deleteAlarmRequestEntity: DeleteAlarmRequestEntity) = execute(
        job = { deleteAlarmUseCase.execute(deleteAlarmRequestEntity)},
        onSuccess = { emitEvent(Event.Success) },
        onFailure = {
            when(it){
                is BadRequestException -> emitEvent(Event.BadRequest)
                is UnauthorizedException -> emitEvent(Event.Unauthorized)
                is ForbiddenException -> emitEvent(Event.Forbidden)
                is NotFoundException -> emitEvent(Event.NotFound)
                is ServerException -> emitEvent(Event.Server)
            }
        }
    )

    sealed class Event {
        object Success: Event()
        object BadRequest : Event()
        object Unauthorized : Event()
        object Forbidden : Event()
        object NotFound : Event()
        object Server : Event()
    }
}