package it.battagliandrea.formula1.domain.usecase

import app.cash.turbine.test
import arrow.core.Either
import arrow.core.right
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import it.battagliandrea.formula1.core.test.MainDispatcherRule
import it.battagliandrea.formula1.data.races.api.repository.ISchedulesRepository
import it.battagliandrea.formula1.domain.models.ErrorType
import it.battagliandrea.formula1.domain.models.Race
import it.battagliandrea.formula1.domain.models.SessionType
import it.battagliandrea.formula1.domain.models.mock.ResultsMock.mockRaceList
import it.battagliandrea.formula1.domain.usecase.internal.GetCurrentScheduleUseCase
import it.battagliandrea.formula1.domain.usecase.internal.GetCurrentScheduleUseCase.Params
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@RunWith(JUnit4::class)
class GetCurrentScheduleUseCaseTest {

    private lateinit var useCase: GetCurrentScheduleUseCase
    private val scheduleRepository: ISchedulesRepository = mockk()

    @get:Rule
    val coroutinesRule = MainDispatcherRule()

    @Before
    fun setup() {
        useCase = GetCurrentScheduleUseCase(
            scheduleRepository = scheduleRepository,
        )
    }

    @Test
    fun executeTest() = runTest {
        val mockFlow = flowOf(mockRaceList().right())

        every { scheduleRepository.getCurrentSchedule() }.returns(mockFlow)

        useCase.execute(
            params = Params,
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            val expectItem = awaitItem()
            require(expectItem.isRight())
            with(expectItem.getOrNull()?.first()) {
                requireNotNull(this)
                assertEquals(2023L, this.season)
                assertEquals(20, this.round)
                assertEquals("interlagos", this.circuit.id)
                assertTrue(this.schedules.containsKey(SessionType.FIRST_PRACTICE))
                assertTrue(this.schedules.containsKey(SessionType.SECOND_PRACTICE))
                assertTrue(this.schedules.containsKey(SessionType.THIRD_PRACTICE))
                assertTrue(this.schedules.containsKey(SessionType.QUALIFYING))
                assertTrue(this.schedules.containsKey(SessionType.RACE))
            }

            awaitComplete()
        }

        verify(atLeast = 1) {
            scheduleRepository.getCurrentSchedule()
        }
    }

    @Test
    fun executeErrorTest() = runTest {
        val mockFlow = flow<Either<ErrorType, List<Race>>> { throw RuntimeException("broken!") }

        every { scheduleRepository.getCurrentSchedule() }.returns(mockFlow)

        useCase.execute(
            params = Params,
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            assertEquals("broken!", awaitError().message)
        }

        verify(atLeast = 1) {
            scheduleRepository.getCurrentSchedule()
        }
    }
}
