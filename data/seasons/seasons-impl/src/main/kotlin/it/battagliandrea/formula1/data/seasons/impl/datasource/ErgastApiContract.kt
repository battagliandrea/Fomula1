package it.battagliandrea.formula1.data.seasons.impl.datasource

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import it.battagliandrea.formula1.core.network.api.models.BaseResponse
import it.battagliandrea.formula1.data.seasons.impl.models.tables.DataSeasonsTableDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ErgastApiContract {

    @GET("seasons.json")
    suspend fun seasons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Either<CallError, BaseResponse<DataSeasonsTableDto>>
}
