package it.battagliandrea.formula1.domain.usecase

import it.battagliandrea.formula1.data.results.api.repository.IResultsRepository
import it.battagliandrea.formula1.domain.models.QueryResult
import it.battagliandrea.formula1.domain.models.Race
import it.battagliandrea.formula1.domain.usecase.GetCurrentLastResultUseCase.Params
import it.battagliandrea.formula1.domain.usecase.internal.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentLastResultUseCase @Inject constructor(
    private val resultsRepository: IResultsRepository,
) : FlowUseCase<Params, Flow<QueryResult<List<Race>>>> {

    object Params

    override fun execute(
        params: Params,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ): Flow<QueryResult<List<Race>>> =
        resultsRepository.getCurrentLastResult(
            onStart = onStart,
            onComplete = onComplete,
            onError = onError,
        )
}
