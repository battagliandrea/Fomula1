package it.battagliandrea.formula1.data.results.api.repository

import it.battagliandrea.formula1.domain.models.QueryResult
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
     * @param onStart A callback function to be executed when the data retrieval process starts.
     * @param onComplete A callback function to be executed when the data retrieval process is completed.
     * @param onError A callback function to handle any error that may occur during data retrieval.
     * @return A [Flow] emitting [QueryResult] objects containing paginated lists of [Race] objects for the specified year and round.
     */
    suspend fun getResults(
        year: Int,
        round: Int,
        limit: Int = 0,
        offset: Int = 50,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<QueryResult<List<Race>>>

    /**
     * Retrieves the last race results.
     *
     * @param onStart A callback function to be executed when the data retrieval process starts.
     * @param onComplete A callback function to be executed when the data retrieval process is completed.
     * @param onError A callback function to handle any error that may occur during data retrieval.
     * @return A [Flow] emitting [QueryResult] containing a list of [Race] objects.
     */
    suspend fun getCurrentLastResult(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<QueryResult<List<Race>>>
}
