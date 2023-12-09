package it.battagliandrea.formula1.domain.usecase

import it.battagliandrea.formula1.data.results.api.repository.IResultsRepository
import it.battagliandrea.formula1.domain.models.Race
import it.battagliandrea.formula1.domain.usecase.GetCurrentLastResultUseCase.Params
import it.battagliandrea.formula1.domain.usecase.internal.UseCase
import javax.inject.Inject

class GetCurrentLastResultUseCase @Inject constructor(
    private val resultsRepository: IResultsRepository,
) : UseCase<Params, List<Race>> {

    object Params

    override fun invoke(params: Params) =
        resultsRepository.getCurrentLastResult()
}
