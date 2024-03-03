package it.battagliandrea.formula1.data.seasons.api

import arrow.core.Either
import it.battagliandrea.formula1.domain.models.ErrorType
import it.battagliandrea.formula1.domain.models.Season
import kotlinx.coroutines.flow.Flow

/**
 * Interface representing a repository for managing seasons data.
 */
interface ISeasonsRepository {

    /**
     * Retrieves a list of seasons.
     *
     * @return A [Flow] emitting a lists of [Season] objects.
     * @return An [Either] containing either an [ErrorType] in case of failure a [Flow] emitting a paginated lists of [Season] objects.
     *
     */
    fun getSeasons(): Flow<Either<ErrorType, List<Season>>>
}
