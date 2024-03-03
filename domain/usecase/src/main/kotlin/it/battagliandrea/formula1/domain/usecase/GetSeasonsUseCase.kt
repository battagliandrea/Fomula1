package it.battagliandrea.formula1.domain.usecase

import arrow.core.Either
import it.battagliandrea.formula1.data.seasons.api.ISeasonsRepository
import it.battagliandrea.formula1.domain.models.ErrorType
import it.battagliandrea.formula1.domain.models.Season
import it.battagliandrea.formula1.domain.usecase.GetSeasonsUseCase.Params
import it.battagliandrea.formula1.domain.usecase.internal.IFlowUseCase
import javax.inject.Inject

class GetSeasonsUseCase @Inject constructor(
    private val seasonsRepository: ISeasonsRepository,
) : IFlowUseCase<Params, Either<ErrorType, List<Season>>> {

    object Params

    override fun execute(params: Params) =
        seasonsRepository.getSeasons()
}
