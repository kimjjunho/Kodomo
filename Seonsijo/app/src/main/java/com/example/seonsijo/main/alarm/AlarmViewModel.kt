package com.example.seonsijo.main.alarm

import androidx.lifecycle.MutableLiveData
import com.example.domain.entity.alarm.GetAlarmEntity
import com.example.domain.entity.alarm.GetAlarmEntity.AlarmData
import com.example.domain.entity.alarm.GetAlarmParam
import com.example.domain.exception.BadRequestException
import com.example.domain.exception.ForbiddenException
import com.example.domain.exception.NotFoundException
import com.example.domain.exception.ServerException
import com.example.domain.exception.UnauthorizedException
import com.example.domain.usecase.alarm.DeleteAlarmUseCase
import com.example.domain.usecase.alarm.GetAlarmUseCase
import com.example.seonsijo.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val getAlarmUseCase: GetAlarmUseCase,
    private val deleteAlarmUseCase: DeleteAlarmUseCase
): BaseViewModel<AlarmViewModel.Event>() {

    //AlarmData(-1,"",0,"","")
    private val _alarm = MutableStateFlow(GetAlarmEntity(listOf()))
    val alarm: StateFlow<GetAlarmEntity> = _alarm

    fun getAlarm(getAlarmParam: GetAlarmParam) = execute(
        job = { getAlarmUseCase.execute(getAlarmParam) },
        onSuccess = { _alarm.tryEmit(it) },
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

    private val _deleteSuccess: MutableLiveData<Int> = MutableLiveData()
    val deleteSuccess = _deleteSuccess

    fun deleteAlarm(alarm_id: Long, position: Int) = execute(
        job = { deleteAlarmUseCase.execute(alarm_id)},
        onSuccess = {
            deleteSuccess.value = position
                    },
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
        object BadRequest : Event()
        object Unauthorized : Event()
        object Forbidden : Event()
        object NotFound : Event()
        object Server : Event()
    }
}