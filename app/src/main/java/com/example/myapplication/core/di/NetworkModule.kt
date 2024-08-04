package com.example.myapplication.core.di

import com.example.myapplication.data.network.DragonBallApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://dragonball-api.com/"


    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }


    @Provides
    fun provideApiService(retrofit: Retrofit): DragonBallApiService {
       return retrofit.create(DragonBallApiService::class.java)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().build()
}