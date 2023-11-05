package it.battagliandrea.formula1.data.results.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.battagliandrea.formula1.data.results.api.repository.IResultsRepository
import it.battagliandrea.formula1.data.results.impl.network.ErgastApiContract
import it.battagliandrea.formula1.data.results.impl.repository.ResultsRepository
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    @Singleton
    fun bindRepository(repository: ResultsRepository): IResultsRepository

    companion object {
        @OptIn(ExperimentalSerializationApi::class)
        @Provides
        @Singleton
        fun provideRetorif(json: Json, callFactory: Call.Factory) =
            Retrofit.Builder()
                .baseUrl(it.battagliandrea.formula1.core.network.api.ERGAST_BASE_URL)
                .callFactory(callFactory)
                .addConverterFactory(
                    json.asConverterFactory("application/json".toMediaType()),
                )
                .build()
                .create(ErgastApiContract::class.java)
    }
}
