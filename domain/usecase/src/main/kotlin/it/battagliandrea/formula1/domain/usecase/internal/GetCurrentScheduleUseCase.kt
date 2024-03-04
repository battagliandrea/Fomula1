package it.battagliandrea.formula1.domain.usecase.internal

import arrow.core.Either
import it.battagliandrea.formula1.data.results.api.repository.ISchedulesRepository
import it.battagliandrea.formula1.domain.models.ErrorType
import it.battagliandrea.formula1.domain.models.Race
import it.battagliandrea.formula1.domain.usecase.internal.GetCurrentScheduleUseCase.Params
import javax.inject.Inject

class GetCurrentScheduleUseCase @Inject constructor(
    private val scheduleRepository: ISchedulesRepository,
) : IFlowUseCase<Params, Either<ErrorType, List<Race>>> {

    object Params

    override fun execute(params: Params) =
        scheduleRepository.getCurrentSchedule()
}
