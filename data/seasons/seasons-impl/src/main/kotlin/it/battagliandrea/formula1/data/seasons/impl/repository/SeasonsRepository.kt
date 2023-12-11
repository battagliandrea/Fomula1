package it.battagliandrea.formula1.data.seasons.impl.repository

import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import it.battagliandrea.formula1.core.dispatcher.api.IoDispatcher
import it.battagliandrea.formula1.core.network.api.extensions.toErrorType
import it.battagliandrea.formula1.core.resource.ErrorType
import it.battagliandrea.formula1.core.resource.Resource
import it.battagliandrea.formula1.core.resource.toResourceError
import it.battagliandrea.formula1.core.resource.toResourceSuccess
import it.battagliandrea.formula1.data.seasons.api.ISeasonsRepository
import it.battagliandrea.formula1.data.seasons.impl.datasource.ErgastApiContract
import it.battagliandrea.formula1.data.seasons.impl.models.tables.mapToDomain
import it.battagliandrea.formula1.domain.models.Season
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeasonsRepository @Inject constructor(
    private val apiContract: ErgastApiContract,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ISeasonsRepository {

    override fun getSeasons() = flow<Resource<List<Season>>> {
        apiContract.seasons()
            .suspendOnSuccess {
                val query = data.mRData.mapToDomain()
                emit(query.data.toResourceSuccess())
            }
            .suspendOnError {
                emit(this.toErrorType().toResourceError())
            }
            .suspendOnException {
                emit(ErrorType.Unknown.toResourceError())
            }
    }
}
