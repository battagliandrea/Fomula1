package it.battagliandrea.formula1.core.network.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object Client {

    const val ERGAST_BASE_URL: String = BuildConfig.BASE_URL

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
