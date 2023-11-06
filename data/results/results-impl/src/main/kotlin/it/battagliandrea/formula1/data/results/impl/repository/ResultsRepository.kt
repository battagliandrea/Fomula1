package it.battagliandrea.formula1.data.results.impl.repository

import it.battagliandrea.formula1.core.dispatcher.api.IoDispatcher
import it.battagliandrea.formula1.data.results.api.repository.IResultsRepository
import it.battagliandrea.formula1.data.results.impl.models.tables.mapToDomain
import it.battagliandrea.formula1.data.results.impl.network.ErgastApiContract
import it.battagliandrea.formula1.domain.models.QueryResult
import it.battagliandrea.formula1.domain.models.Race
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResultsRepository @Inject constructor(
    private val apiContract: ErgastApiContract,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : IResultsRepository {

    override suspend fun getResults(
        year: Int,
        round: Int,
        limit: Int,
        offset: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<QueryResult<List<Race>>> = flow {
        val response = apiContract.results(year = year, round = round, limit = limit, offset = offset)
        if (response.isSuccessful) {
            emit(response.body()?.mRData.mapToDomain())
        } else {
            onError("Error!")
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)

    override suspend fun getCurrentLastResult(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<QueryResult<List<Race>>> = flow {
        val response = apiContract.currentLastResults()
        if (response.isSuccessful) {
            emit(response.body()?.mRData.mapToDomain())
        } else {
            onError("Error!")
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
