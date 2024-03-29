package it.battagliandrea.formula1.data.seasons.di

import arrow.retrofit.adapter.either.EitherCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.battagliandrea.formula1.core.network.api.Client
import it.battagliandrea.formula1.data.seasons.impl.datasource.ErgastApiContract
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesErgastApiContract(
        json: Json,
        callFactory: Call.Factory,
    ): ErgastApiContract = Retrofit.Builder()
        .baseUrl(Client.ERGAST_BASE_URL)
        .callFactory(callFactory)
        .addConverterFactory(
            json.asConverterFactory("application/json".toMediaType()),
        )
        .addCallAdapterFactory(EitherCallAdapterFactory.create())
        .build()
        .create(ErgastApiContract::class.java)
}
