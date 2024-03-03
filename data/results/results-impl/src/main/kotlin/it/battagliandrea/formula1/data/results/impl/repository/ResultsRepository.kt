package it.battagliandrea.formula1.data.results.impl.repository

import arrow.core.left
import arrow.core.right
import it.battagliandrea.formula1.core.dispatcher.api.IoDispatcher
import it.battagliandrea.formula1.data.core.toErrorType
import it.battagliandrea.formula1.data.results.api.repository.IResultsRepository
import it.battagliandrea.formula1.data.results.impl.datasource.ErgastApiContract
import it.battagliandrea.formula1.data.results.impl.models.tables.mapToDomain
import it.battagliandrea.formula1.domain.models.ErrorType.Unknown
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
    ) = flow {
        try {
            apiContract.results(year = year, round = round, limit = limit, offset = offset)
                .map { response ->
                    val query = response.mRData.mapToDomain()
                    emit(query.data.right())
                }
                .mapLeft { error ->
                    emit(error.toErrorType().left())
                }
        } catch (e: Exception) {
            Unknown.left()
        }
    }.flowOn(ioDispatcher)

    override fun getCurrentLastResult() = flow {
        try {
            apiContract.currentLastResults()
                .map { response ->
                    val query = response.mRData.mapToDomain()
                    emit(query.data.right())
                }
                .mapLeft { error ->
                    emit(error.toErrorType().left())
                }
        } catch (e: Exception) {
            Unknown.left()
        }
    }.flowOn(ioDispatcher)
}
