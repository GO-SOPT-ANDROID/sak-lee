package org.android.go.sopt.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.go.sopt.data.Api.HomeApiService
import org.android.go.sopt.data.Api.SignApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideSignApiService(@SoptRetrofit retrofit: Retrofit): SignApiService = retrofit.create(SignApiService::class.java)
    @Provides
    @Singleton
    fun provideHomeApiService(@ReqresRetrofit retrofit: Retrofit): HomeApiService = retrofit.create(HomeApiService::class.java)

}