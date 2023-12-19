package it.battagliandrea.formula1.data.seasons.api

import it.battagliandrea.formula1.core.resource.Resource
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
     */
    fun getSeasons(): Flow<Resource<List<Season>>>
}
