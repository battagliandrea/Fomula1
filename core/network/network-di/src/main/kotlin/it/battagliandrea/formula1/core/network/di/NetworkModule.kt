package it.battagliandrea.formula1.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.battagliandrea.formula1.core.network.api.Client
import okhttp3.Call
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): Call.Factory = Client.httpClient
}
