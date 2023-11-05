package it.battagliandrea.formula1.data.results.impl.repository

import it.battagliandrea.formula1.data.results.api.repository.IResultsRepository
import it.battagliandrea.formula1.data.results.impl.models.tables.mapToDomain
import it.battagliandrea.formula1.data.results.impl.network.ErgastApiContract
import it.battagliandrea.formula1.domain.models.QueryResult
import it.battagliandrea.formula1.domain.models.Race
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResultsRepository @Inject constructor(
    private val apiContract: ErgastApiContract,
) : IResultsRepository {

    override suspend fun getResults(
        year: Int,
        round: Int,
        limit: Int,
        offset: Int,
    ): QueryResult<List<Race>> =
        apiContract.results(year = year, round = round, limit = limit, offset = offset).body()?.mRData.mapToDomain()

    override suspend fun getCurrentLastResult(): QueryResult<List<Race>> =
        apiContract.currentLastResults().body()?.mRData.mapToDomain()
}
