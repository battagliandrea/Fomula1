package it.battagliandrea.formula1.domain.usecase

import app.cash.turbine.test
import arrow.core.Either
import arrow.core.right
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import it.battagliandrea.formula1.core.test.MainDispatcherRule
import it.battagliandrea.formula1.data.seasons.api.ISeasonsRepository
import it.battagliandrea.formula1.domain.models.ErrorType
import it.battagliandrea.formula1.domain.models.Season
import it.battagliandrea.formula1.domain.models.SeasonsMock.mockSeasonsList
import it.battagliandrea.formula1.domain.usecase.GetSeasonsUseCase.Params
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
class GetSeasonsUseCaseTest {

    private lateinit var useCase: GetSeasonsUseCase
    private val seasonsRepository: ISeasonsRepository = mockk()

    @get:Rule
    val coroutinesRule = MainDispatcherRule()

    @Before
    fun setup() {
        useCase = GetSeasonsUseCase(
            seasonsRepository = seasonsRepository,
        )
    }

    @Test
    fun executeTest() = runTest {
        val mockFlow = flowOf(mockSeasonsList().right())

        every { seasonsRepository.getSeasons() }.returns(mockFlow)

        useCase.execute(
            params = Params,
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            val expectItem = awaitItem()
            require(expectItem.isRight())
            with(expectItem.getOrNull()) {
                assertEquals(2024, this?.first()?.year)
            }

            awaitComplete()
        }

        verify(atLeast = 1) {
            seasonsRepository.getSeasons()
        }
    }

    @Test
    fun executeErrorTest() = runTest {
        val mockFlow = flow<Either<ErrorType, List<Season>>> { throw RuntimeException("broken!") }

        every { seasonsRepository.getSeasons() }.returns(mockFlow)

        useCase.execute(
            params = Params,
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            assertEquals("broken!", awaitError().message)
        }

        verify(atLeast = 1) {
            seasonsRepository.getSeasons()
        }
    }
}
