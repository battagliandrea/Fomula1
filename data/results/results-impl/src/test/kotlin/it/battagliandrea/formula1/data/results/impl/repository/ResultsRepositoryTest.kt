package it.battagliandrea.formula1.data.results.impl.repository

import app.cash.turbine.test
import arrow.core.right
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import it.battagliandrea.formula1.core.network.api.models.BaseResponse
import it.battagliandrea.formula1.core.test.MainDispatcherRule
import it.battagliandrea.formula1.data.results.api.repository.IResultsRepository
import it.battagliandrea.formula1.data.results.impl.MockUtils
import it.battagliandrea.formula1.data.results.impl.datasource.ErgastApiContract
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
class ResultsRepositoryTest {

    private lateinit var repository: IResultsRepository
    private var apiContract: ErgastApiContract = mockk()

    @get:Rule
    val coroutinesRule = MainDispatcherRule()

    @Before
    fun setup() {
        repository = ResultsRepository(
            apiContract = apiContract,
            ioDispatcher = coroutinesRule.testDispatcher,
        )
    }

    @Test
    fun getResultsFromNetworkTest() = runTest {
        val mockData = BaseResponse(
            mRData = MockUtils.mockDataRaceTableDto(),
        )

        coEvery { apiContract.results(year = 2023, round = 20, limit = 20, offset = 0) } returns(mockData.right())

        repository.getResults(
            year = 2023,
            round = 20,
            limit = 20,
            offset = 0,
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            val expectItem = awaitItem()
            require(expectItem.isRight())

            with(expectItem.getOrNull()?.first()) {
                assertEquals(this?.season, 2023L)
                assertEquals(this?.round, 20)
                assertEquals(this?.circuit?.id, "interlagos")
                assertEquals(this?.results?.first()?.driver?.id, "max_verstappen")
                assertEquals(this?.results?.first()?.constructor?.id, "red_bull")
            }

            awaitComplete()
        }

        coVerify(atLeast = 1) { apiContract.results(year = 2023, round = 20, limit = 20, offset = 0) }
    }

    @Test
    fun getCurrentLastResultFromNetworkTest() = runTest {
        val mockData = BaseResponse(
            mRData = MockUtils.mockDataRaceTableDto(),
        )

        coEvery { apiContract.currentLastResults() } returns (mockData.right())

        repository.getCurrentLastResult().test(2.toDuration(DurationUnit.SECONDS)) {
            val expectItem = awaitItem()

            require(expectItem.isRight())

            with(expectItem.getOrNull()?.first()) {
                assertEquals(this?.season, 2023L)
                assertEquals(this?.round, 20)
                assertEquals(this?.circuit?.id, "interlagos")
                assertEquals(this?.results?.first()?.driver?.id, "max_verstappen")
                assertEquals(this?.results?.first()?.constructor?.id, "red_bull")
            }

            awaitComplete()
        }

        coVerify(atLeast = 1) { apiContract.currentLastResults() }
    }
}
