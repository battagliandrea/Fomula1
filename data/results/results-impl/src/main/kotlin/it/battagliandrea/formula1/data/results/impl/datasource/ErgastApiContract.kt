package it.battagliandrea.formula1.data.results.impl.datasource

import com.skydoves.sandwich.ApiResponse
import it.battagliandrea.formula1.core.network.api.models.BaseResponse
import it.battagliandrea.formula1.data.results.impl.models.tables.DataRaceTableDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ErgastApiContract {

    @GET("{year}/{round}/results.json")
    suspend fun results(
        @Path("year") year: Int,
        @Path("round") round: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): ApiResponse<BaseResponse<DataRaceTableDto>>

    @GET("current/last/results.json")
    suspend fun currentLastResults(): ApiResponse<BaseResponse<DataRaceTableDto>>
}
