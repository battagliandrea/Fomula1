package it.battagliandrea.formula1.data.results.impl.repository

import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import it.battagliandrea.formula1.core.dispatcher.api.IoDispatcher
import it.battagliandrea.formula1.core.network.api.mapper.ApiResponseMapper
import it.battagliandrea.formula1.data.results.api.repository.IResultsRepository
import it.battagliandrea.formula1.data.results.impl.datasource.ErgastApiContract
import it.battagliandrea.formula1.data.results.impl.models.tables.mapToDomain
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
        apiContract.results(year = year, round = round, limit = limit, offset = offset)
            .suspendOnSuccess {
                emit(data.mRData.mapToDomain())
            }
            .onError {
                map(ApiResponseMapper) { onError(this) }
            }
            .onException { onError(message) }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)

    override suspend fun getCurrentLastResult(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<QueryResult<List<Race>>> = flow {
        apiContract.currentLastResults()
            .suspendOnSuccess {
                emit(data.mRData.mapToDomain())
            }
            .onError {
                map(ApiResponseMapper) { onError(this) }
            }
            .onException { onError(message) }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
