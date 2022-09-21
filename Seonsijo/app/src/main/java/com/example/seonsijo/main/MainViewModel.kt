package com.example.seonsijo.main

import com.example.domain.ScheduleUseCase
import com.example.domain.entity.ScheduleRequestEntity
import com.example.domain.exception.BadRequestException
import com.example.domain.exception.ConflictException
import com.example.domain.exception.ForbiddenException
import com.example.domain.exception.NotFoundException
import com.example.domain.exception.ServerException
import com.example.domain.exception.TimeoutException
import com.example.domain.exception.TooManyRequestException
import com.example.domain.exception.UnauthorizedException
import com.example.seonsijo.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val scheduleUseCase: ScheduleUseCase
) : BaseViewModel<MainViewModel.Event>() {

    fun getSchedule(scheduleEntity: ScheduleRequestEntity) = execute(
        job = { scheduleUseCase.execute(scheduleEntity) },
        onSuccess = { emitEvent(Event.Success) },
        onFailure = {
            when (it) {
                is BadRequestException -> emitEvent(Event.BadRequest)
                is ForbiddenException -> emitEvent(Event.Forbidden)
                is NotFoundException-> emitEvent(Event.NotFound)
                is ConflictException -> emitEvent(Event.ConflictAccountId)
                is ServerException -> emitEvent(Event.Server)
            }
        }
    )

    sealed class Event {
        object Success : Event()
        object BadRequest : Event()
        object Forbidden: Event()
        object NotFound: Event()
        object ConflictAccountId : Event()
        object Server: Event()
    }
}