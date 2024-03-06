package it.battagliandrea.formula1.data.races.impl.repository

import arrow.core.left
import arrow.core.right
import it.battagliandrea.formula1.core.dispatcher.api.IoDispatcher
import it.battagliandrea.formula1.data.core.toErrorType
import it.battagliandrea.formula1.data.races.api.repository.ISchedulesRepository
import it.battagliandrea.formula1.data.races.impl.datasource.ErgastApiContract
import it.battagliandrea.formula1.data.races.impl.models.tables.mapToDomain
import it.battagliandrea.formula1.domain.models.ErrorType.Unknown
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchedulesRepository @Inject constructor(
    private val apiContract: ErgastApiContract,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ISchedulesRepository {

    override fun getCurrentSchedule() = flow {
        try {
            apiContract.currentSchedule()
                .map { response ->
                    val query = response.mRData.mapToDomain()
                    emit(query.data.right())
                }
                .mapLeft { error ->
                    emit(error.toErrorType().left())
                }
        } catch (e: Exception) {
            emit(Unknown.left())
        }
    }.flowOn(ioDispatcher)
}
