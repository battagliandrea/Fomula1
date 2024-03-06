package it.battagliandrea.formula1.data.results.api.repository

import arrow.core.Either
import it.battagliandrea.formula1.domain.models.ErrorType
import it.battagliandrea.formula1.domain.models.Race
import kotlinx.coroutines.flow.Flow

/**
 * Interface representing a repository for managing schedules data.
 */
interface ISchedulesRepository {

    /**
     * Retrieves the race schedules.
     *
     * @return A [Flow] emitting a lists of [Race] objects.
     * @return An [Either] containing either an [ErrorType] in case of failure a [Flow] emitting a paginated lists of [Race] objects.
     *
     */
    fun getCurrentSchedule(): Flow<Either<ErrorType, List<Race>>>
}
