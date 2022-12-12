package com.example.seonsijo.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.entity.schedule.ScheduleEntity
import com.example.domain.entity.schedule.ScheduleParam
import com.example.domain.usecase.schedule.ScheduleUseCase
import com.example.domain.exception.BadRequestException
import com.example.domain.exception.ConflictException
import com.example.domain.exception.ForbiddenException
import com.example.domain.exception.NotFoundException
import com.example.domain.exception.ServerException
import com.example.domain.usecase.schedule.AutoScheduleUseCase
import com.example.seonsijo.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val scheduleUseCase: ScheduleUseCase,
    private val autoScheduleUseCase: AutoScheduleUseCase
) : BaseViewModel<MainViewModel.Event>() {

//    private val _schedule = MutableStateFlow(ScheduleEntity(listOf(), listOf(), listOf(), listOf(), listOf()))
//    val schedule: StateFlow<ScheduleEntity> = _schedule

    private val _schedule: MutableLiveData<ScheduleEntity> = MutableLiveData()
    val schedule: LiveData<ScheduleEntity> = _schedule

    fun getSchedule(scheduleParam: ScheduleParam) = execute(
        job = { scheduleUseCase.execute(scheduleParam) },
        onSuccess = {
            //_schedule.tryEmit(it)
            _schedule.value = it
        },
        onFailure = {
            when (it) {
                is BadRequestException -> emitEvent(Event.BadRequest)
                is ForbiddenException -> emitEvent(Event.Forbidden)
                is NotFoundException -> emitEvent(Event.NotFound)
                is ConflictException -> emitEvent(Event.ConflictAccountId)
                is ServerException -> emitEvent(Event.Server)
            }
        }
    )

    fun autoSchedule() = execute(
        job = { autoScheduleUseCase.execute(Unit) },
        onSuccess = {
           // _schedule.tryEmit(it)
            _schedule.value = it
        },
        onFailure = { }
    )

    sealed class Event {
        object BadRequest : Event()
        object Forbidden : Event()
        object NotFound : Event()
        object ConflictAccountId : Event()
        object Server : Event()
    }
}