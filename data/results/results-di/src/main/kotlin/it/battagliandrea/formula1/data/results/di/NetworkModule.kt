package it.battagliandrea.formula1.data.results.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.battagliandrea.formula1.core.network.api.Client
import it.battagliandrea.formula1.data.results.impl.datasource.ErgastApiContract
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @OptIn(ExperimentalSerializationApi::class)
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
        .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
        .build()
        .create(ErgastApiContract::class.java)
}
