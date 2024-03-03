package it.battagliandrea.formula1.domain.usecase

import app.cash.turbine.test
import arrow.core.Either
import arrow.core.right
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import it.battagliandrea.formula1.core.test.MainDispatcherRule
import it.battagliandrea.formula1.data.results.api.repository.IResultsRepository
import it.battagliandrea.formula1.domain.models.ErrorType
import it.battagliandrea.formula1.domain.models.Race
import it.battagliandrea.formula1.domain.models.ResultsMock.mockRaceList
import it.battagliandrea.formula1.domain.usecase.GetCurrentLastResultUseCase.Params
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@RunWith(JUnit4::class)
class GetCurrentLastResultUseCaseTest {

    private lateinit var useCase: GetCurrentLastResultUseCase
    private val resultRepository: IResultsRepository = mockk()

    @get:Rule
    val coroutinesRule = MainDispatcherRule()

    @Before
    fun setup() {
        useCase = GetCurrentLastResultUseCase(
            resultsRepository = resultRepository,
        )
    }

    @Test
    fun executeTest() = runTest {
        val mockFlow = flowOf(mockRaceList().right())

        every { resultRepository.getCurrentLastResult() }.returns(mockFlow)

        useCase.execute(
            params = Params,
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            val expectItem = awaitItem()
            require(expectItem.isRight())
            with(expectItem.getOrNull()?.first()) {
                assertEquals(2023L, this?.season)
                assertEquals(20, this?.round)
                assertEquals("interlagos", this?.circuit?.id)
                assertEquals("max_verstappen", this?.results?.first()?.driver?.id)
                assertEquals("red_bull", this?.results?.first()?.constructor?.id)
            }

            awaitComplete()
        }

        verify(atLeast = 1) {
            resultRepository.getCurrentLastResult()
        }
    }

    @Test
    fun executeErrorTest() = runTest {
        val mockFlow = flow<Either<ErrorType, List<Race>>> { throw RuntimeException("broken!") }

        every { resultRepository.getCurrentLastResult() }.returns(mockFlow)

        useCase.execute(
            params = Params,
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            assertEquals("broken!", awaitError().message)
        }

        verify(atLeast = 1) {
            resultRepository.getCurrentLastResult()
        }
    }
}
