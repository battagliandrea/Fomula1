package it.battagliandrea.formula1.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.battagliandrea.formula1.core.network.api.NetworkProvider
import kotlinx.serialization.json.Json
import okhttp3.Call
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesJson(): Json = NetworkProvider.jsonConfiguration

    @Provides
    @Singleton
    fun provideOkHttpClient(): Call.Factory = NetworkProvider.httpClient
}
