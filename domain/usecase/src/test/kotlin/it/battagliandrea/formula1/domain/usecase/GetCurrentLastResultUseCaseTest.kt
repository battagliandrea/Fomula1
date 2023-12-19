package it.battagliandrea.formula1.domain.usecase

import app.cash.turbine.test
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import it.battagliandrea.formula1.core.resource.Resource
import it.battagliandrea.formula1.core.resource.toResourceSuccess
import it.battagliandrea.formula1.core.test.MainDispatcherRule
import it.battagliandrea.formula1.data.results.api.repository.IResultsRepository
import it.battagliandrea.formula1.domain.models.Race
import it.battagliandrea.formula1.domain.models.ResultsMock.mockRaceList
import it.battagliandrea.formula1.domain.usecase.GetCurrentLastResultUseCase.Params
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
        val mockFlow = flowOf(mockRaceList().toResourceSuccess())

        every { resultRepository.getCurrentLastResult() }.returns(mockFlow)

        useCase.invoke(
            params = Params,
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            val expectItem = awaitItem()
            require(expectItem is Resource.Success)
            assertEquals(2023, expectItem.data.first().season)
            assertEquals(20, expectItem.data.first().round)
            assertEquals("interlagos", expectItem.data.first().circuit.id)
            assertTrue(expectItem.data.first().results.isNotEmpty())
            assertEquals("max_verstappen", expectItem.data.first().results.first().driver.id)
            assertEquals("red_bull", expectItem.data.first().results.first().constructor.id)
            awaitComplete()
        }

        verify(atLeast = 1) {
            resultRepository.getCurrentLastResult()
        }
    }

    @Test
    fun executeErrorTest() = runTest {
        val mockFlow = flow<Resource<List<Race>>> { throw RuntimeException("broken!") }

        every { resultRepository.getCurrentLastResult() }.returns(mockFlow)

        useCase.invoke(
            params = Params,
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            assertEquals("broken!", awaitError().message)
        }

        verify(atLeast = 1) {
            resultRepository.getCurrentLastResult()
        }
    }
}
