package it.battagliandrea.formula1.domain.usecase

import arrow.core.Either
import it.battagliandrea.formula1.data.results.api.repository.IResultsRepository
import it.battagliandrea.formula1.domain.models.ErrorType
import it.battagliandrea.formula1.domain.models.Result
import it.battagliandrea.formula1.domain.usecase.GetCurrentLastResultUseCase.Params
import it.battagliandrea.formula1.domain.usecase.internal.IFlowUseCase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCurrentLastResultUseCase @Inject constructor(
    private val resultsRepository: IResultsRepository,
) : IFlowUseCase<Params, Either<ErrorType, List<Result>>> {

    object Params

    override fun execute(params: Params) =
        resultsRepository.getCurrentLastResult()
            .map { either ->
                either.map { races ->
                    races.firstOrNull()?.results.orEmpty()
                }
            }
}
