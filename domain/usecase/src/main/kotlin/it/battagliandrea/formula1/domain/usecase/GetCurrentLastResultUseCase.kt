package it.battagliandrea.formula1.domain.usecase

import arrow.core.Either
import it.battagliandrea.formula1.data.results.api.repository.IResultsRepository
import it.battagliandrea.formula1.domain.models.ErrorType
import it.battagliandrea.formula1.domain.models.Race
import it.battagliandrea.formula1.domain.usecase.GetCurrentLastResultUseCase.Params
import it.battagliandrea.formula1.domain.usecase.internal.IFlowUseCase
import javax.inject.Inject

class GetCurrentLastResultUseCase @Inject constructor(
    private val resultsRepository: IResultsRepository,
) : IFlowUseCase<Params, Either<ErrorType, List<Race>>> {

    object Params

    override fun execute(params: Params) =
        resultsRepository.getCurrentLastResult()
}
