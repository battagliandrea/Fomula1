package it.battagliandrea.formula1.core.network.api

import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object NetworkProvider {

    val jsonConfiguration = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    val httpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(getLoggingInterceptor())
        .build()

    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingBody = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingBody.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingBody.level = HttpLoggingInterceptor.Level.NONE
        }
        return loggingBody
    }
}
