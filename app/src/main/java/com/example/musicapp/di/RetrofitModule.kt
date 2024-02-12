    package com.example.musicapp.di

import com.example.musicapp.data.cloud.service.MusicService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://parseapi.back4app.com/classes/"
const val APPLICATION_ID = "3PGA3HsBWXsgAEjNd9vmo3It1SuNU2kER72a2azm"
private const val REST_API_KEY = "8bTk97Skfg5cYBwze9AUawJTL3g2gurVW71BfQ9t"
const val CLIENT_KEY = "2LO7VYXR9ma9fw8m1sYCZMAvb6rUTCSo5f46NuRu"
const val PARSE_BASE_URL = "https://parseapi.back4app.com"

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(Interceptor { chain ->
                        val request = chain.request()
                            .newBuilder()
                            .addHeader(
                                name = "X-Parse-Application-Id",
                                value = APPLICATION_ID
                            )
                            .addHeader(
                                name = "X-Parse-REST-API-Key",
                                value = REST_API_KEY
                            )
                            .addHeader(
                                name = "Content-Type",
                                value = "application/json"
                            )
                            .build()
                        return@Interceptor chain.proceed(request = request)
                    }).build()
            ).build()
    }

    @Provides
    fun provideMusicService(
        retrofit: Retrofit
    ):MusicService = retrofit.create(MusicService::class.java)
}
