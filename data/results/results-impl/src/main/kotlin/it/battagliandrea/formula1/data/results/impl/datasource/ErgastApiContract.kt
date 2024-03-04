package it.battagliandrea.formula1.data.results.impl.datasource

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import it.battagliandrea.formula1.core.network.api.models.BaseResponse
import it.battagliandrea.formula1.data.results.impl.models.tables.DataRaceTableDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ErgastApiContract {

    @GET("current.json")
    suspend fun currentSchedule(): Either<CallError, BaseResponse<DataRaceTableDto>>

    @GET("{year}/{round}/results.json")
    suspend fun results(
        @Path("year") year: Int,
        @Path("round") round: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Either<CallError, BaseResponse<DataRaceTableDto>>

    @GET("current/last/results.json")
    suspend fun currentLastResults(): Either<CallError, BaseResponse<DataRaceTableDto>>
}
