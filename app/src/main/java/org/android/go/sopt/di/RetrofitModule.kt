package org.android.go.sopt.di

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.android.go.sopt.util.API.API_TAG
import org.android.go.sopt.util.API.BASE_URL
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            when {
                message.isJsonObject() ->
                    Log.d(API_TAG, JSONObject(message).toString(4))
                message.isJsonArray() ->
                    Log.d(API_TAG, JSONArray(message).toString(4))
                else -> {
                    Log.d(API_TAG, "CONNECTION INFO -> $message")
                }
            }
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    private fun String?.isJsonObject(): Boolean = this?.startsWith("{") == true && this.endsWith("}")
    private fun String?.isJsonArray(): Boolean = this?.startsWith("[") == true && this.endsWith("]")

}

