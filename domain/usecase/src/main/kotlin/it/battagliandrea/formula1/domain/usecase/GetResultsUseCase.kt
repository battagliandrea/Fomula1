package it.battagliandrea.formula1.domain.usecase

import arrow.core.Either
import it.battagliandrea.formula1.data.results.api.repository.IResultsRepository
import it.battagliandrea.formula1.domain.models.ErrorType
import it.battagliandrea.formula1.domain.models.Race
import it.battagliandrea.formula1.domain.usecase.GetResultsUseCase.Params
import it.battagliandrea.formula1.domain.usecase.internal.IFlowUseCase
import javax.inject.Inject

class GetResultsUseCase @Inject constructor(
    private val resultsRepository: IResultsRepository,
) : IFlowUseCase<Params, Either<ErrorType, List<Race>>> {

    data class Params(val year: Int, val round: Int)

    override fun execute(params: Params) =
        resultsRepository.getResults(
            year = params.year,
            round = params.round,
        )
}
