package it.battagliandrea.formula1.data.results.api.repository

import arrow.core.Either
import it.battagliandrea.formula1.domain.models.ErrorType
import it.battagliandrea.formula1.domain.models.Race
import kotlinx.coroutines.flow.Flow

/**
 * Interface representing a repository for managing race results data.
 */
interface IResultsRepository {

    /**
     * Retrieves a list of race results for a specific year and round.
     *
     * @param year The year for which race results are to be retrieved.
     * @param round The round number for which race results are to be retrieved.
     * @param limit The maximum number of results to retrieve in a single request.
     * @param offset The offset to start retrieving results from the list.
     * @return An [Either] containing either an [ErrorType] in case of failure a [Flow] emitting a paginated lists of [Race] objects for the specified year and round.
     */
    fun getResults(
        year: Int,
        round: Int,
        limit: Int = 50,
        offset: Int = 0,
    ): Flow<Either<ErrorType, List<Race>>>

    /**
     * Retrieves the last race results.
     *
     * @return An [Either] containing either an [ErrorType] in case of failure a [Flow] emitting a list of [Race] objects..
     */
    fun getCurrentLastResult(): Flow<Either<ErrorType, List<Race>>>
}
