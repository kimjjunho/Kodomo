package com.example.di

import android.util.Log
import com.example.data.remote.api.AlarmAPI
import com.example.data.remote.api.ScheduleAPI
import com.example.data.remote.api.SignUpAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://2cc0-211-36-142-145.jp.ngrok.io/"

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideOkHttpclient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideScheduleAPI(
        retrofit: Retrofit
    ): ScheduleAPI =
        retrofit.create(ScheduleAPI::class.java)

    @Singleton
    @Provides
    fun provideAlarmAPI(
        retrofit: Retrofit
    ): AlarmAPI =
        retrofit.create(AlarmAPI::class.java)

    @Singleton
    @Provides
    fun provideSignUpAPI(
        retrofit: Retrofit
    ): SignUpAPI =
        retrofit.create(SignUpAPI::class.java)
}