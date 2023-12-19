package it.battagliandrea.formula1.domain.usecase

import it.battagliandrea.formula1.data.seasons.api.ISeasonsRepository
import it.battagliandrea.formula1.domain.models.Season
import it.battagliandrea.formula1.domain.usecase.GetSeasonsUseCase.Params
import it.battagliandrea.formula1.domain.usecase.internal.UseCase
import javax.inject.Inject

class GetSeasonsUseCase @Inject constructor(
    private val seasonsRepository: ISeasonsRepository,
) : UseCase<Params, List<Season>> {

    object Params

    override fun invoke(params: Params) =
        seasonsRepository.getSeasons()
}
