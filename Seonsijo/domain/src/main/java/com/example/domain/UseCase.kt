package com.example.domain

abstract class UseCase<I,O> {
    abstract suspend fun execute(data: I): O
}