package it.battagliandrea.formula1.domain.usecase

import it.battagliandrea.formula1.data.results.api.repository.IResultsRepository
import it.battagliandrea.formula1.domain.models.QueryResult
import it.battagliandrea.formula1.domain.models.Race
import it.battagliandrea.formula1.domain.usecase.GetResultsUseCase.Params
import it.battagliandrea.formula1.domain.usecase.internal.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetResultsUseCase @Inject constructor(
    private val resultsRepository: IResultsRepository,
) : FlowUseCase<Params, Flow<QueryResult<List<Race>>>> {

    data class Params(val year: Int, val round: Int)

    override fun execute(
        params: Params,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<QueryResult<List<Race>>> = with(params) {
        resultsRepository.getResults(
            year = year,
            round = round,
            onStart = onStart,
            onComplete = onComplete,
            onError = onError,
        )
    }
}
