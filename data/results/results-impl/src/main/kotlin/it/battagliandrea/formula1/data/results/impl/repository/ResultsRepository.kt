package it.battagliandrea.formula1.data.results.impl.repository

import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import it.battagliandrea.formula1.core.dispatcher.api.IoDispatcher
import it.battagliandrea.formula1.core.network.api.extensions.toErrorType
import it.battagliandrea.formula1.core.resource.ErrorType
import it.battagliandrea.formula1.core.resource.Resource
import it.battagliandrea.formula1.core.resource.toResourceError
import it.battagliandrea.formula1.core.resource.toResourceSuccess
import it.battagliandrea.formula1.data.results.api.repository.IResultsRepository
import it.battagliandrea.formula1.data.results.impl.datasource.ErgastApiContract
import it.battagliandrea.formula1.data.results.impl.models.tables.mapToDomain
import it.battagliandrea.formula1.domain.models.Race
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResultsRepository @Inject constructor(
    private val apiContract: ErgastApiContract,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : IResultsRepository {

    override fun getResults(
        year: Int,
        round: Int,
        limit: Int,
        offset: Int,
    ) = flow<Resource<List<Race>>> {
        apiContract
            .results(year = year, round = round, limit = limit, offset = offset)
            .suspendOnSuccess {
                val query = data.mRData.mapToDomain()
                emit(query.data.toResourceSuccess())
            }
            .suspendOnError {
                emit(this.toErrorType().toResourceError())
            }
            .suspendOnException { emit(ErrorType.Unknown.toResourceError()) }
    }.flowOn(ioDispatcher)

    override fun getCurrentLastResult() = flow<Resource<List<Race>>> {
        apiContract.currentLastResults()
            .suspendOnSuccess {
                val query = data.mRData.mapToDomain()
                emit(query.data.toResourceSuccess())
            }
            .suspendOnError {
                emit(this.toErrorType().toResourceError())
            }
            .suspendOnException { emit(ErrorType.Unknown.toResourceError()) }
    }.flowOn(ioDispatcher)
}
