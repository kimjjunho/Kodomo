package com.example.data.remote.api

import com.example.data.remote.request.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpAPI {

    @POST("users/login")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    )
}