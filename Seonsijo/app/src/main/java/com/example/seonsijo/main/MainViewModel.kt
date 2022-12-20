package com.example.seonsijo.main

import android.util.Log
import com.example.domain.entity.schedule.ScheduleEntity
import com.example.domain.entity.schedule.ScheduleParam
import com.example.domain.usecase.schedule.ScheduleUseCase
import com.example.domain.exception.BadRequestException
import com.example.domain.exception.ForbiddenException
import com.example.domain.exception.NoInternetException
import com.example.domain.exception.NotFoundException
import com.example.domain.exception.ServerException
import com.example.domain.exception.TooManyRequestException
import com.example.domain.usecase.schedule.AutoScheduleUseCase
import com.example.seonsijo.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val scheduleUseCase: ScheduleUseCase,
    private val autoScheduleUseCase: AutoScheduleUseCase
) : BaseViewModel<MainViewModel.Event>() {

    private val _schedule = MutableStateFlow(ScheduleEntity(listOf(), listOf(), listOf(), listOf(), listOf()))
    val schedule: StateFlow<ScheduleEntity> = _schedule

    fun getSchedule(scheduleParam: ScheduleParam) = execute(
        job = {
            try {
                scheduleUseCase.execute(scheduleParam)
            } catch (e: java.net.ConnectException) {
                autoScheduleUseCase.execute(Unit)
            } catch (e: UnknownHostException) {
                autoScheduleUseCase.execute(Unit)
            }
        },
        onSuccess = { _schedule.tryEmit(it) },
        onFailure = {
            when (it) {
                is BadRequestException -> emitEvent(Event.BadRequest)
                is ForbiddenException -> emitEvent(Event.Forbidden)
                is NotFoundException -> emitEvent(Event.NotFound)
                is TooManyRequestException -> emitEvent(Event.ManyRequest)
                is ServerException -> emitEvent(Event.Server)
                is NoInternetException -> emitEvent(Event.NoInternet)
            }
            _schedule.value = ScheduleEntity(listOf(), listOf(), listOf(), listOf(), listOf())
        }
    )

    fun localSchedule() = execute(
        job = { autoScheduleUseCase.execute(Unit) },
        onSuccess = { _schedule.tryEmit(it) },
        onFailure = {  }
    )

    sealed class Event {
        object BadRequest : Event()
        object Forbidden : Event()
        object NotFound : Event()
        object ManyRequest: Event()
        object Server : Event()
        object NoInternet: Event()
    }
}