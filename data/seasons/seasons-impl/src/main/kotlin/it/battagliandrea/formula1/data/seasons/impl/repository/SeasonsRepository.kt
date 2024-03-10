package it.battagliandrea.formula1.data.seasons.impl.repository

import arrow.core.left
import arrow.core.right
import it.battagliandrea.formula1.core.dispatcher.api.IoDispatcher
import it.battagliandrea.formula1.data.core.toErrorType
import it.battagliandrea.formula1.data.seasons.api.ISeasonsRepository
import it.battagliandrea.formula1.data.seasons.impl.datasource.ErgastApiContract
import it.battagliandrea.formula1.data.seasons.impl.models.tables.mapToDomain
import it.battagliandrea.formula1.domain.models.ErrorType.Unknown
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeasonsRepository @Inject constructor(
    private val apiContract: ErgastApiContract,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ISeasonsRepository {

    override fun getSeasons() = flow {
        try {
            apiContract.seasons(limit = DEFAULT_LIMIT, offset = DEFAULT_OFFSET)
                .map { response ->
                    val query = response.mRData.mapToDomain()
                    emit(query.data.right())
                }
                .mapLeft { error ->
                    emit(error.toErrorType().left())
                }
        } catch (e: Exception) {
            emit(Unknown(e.message).left())
        }
    }.flowOn(ioDispatcher)

    companion object {
        private const val DEFAULT_LIMIT = 100
        private const val DEFAULT_OFFSET = 0
    }
}
