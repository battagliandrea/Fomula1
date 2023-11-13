package it.battagliandrea.formula1.data.results.impl

import app.cash.turbine.test
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.responseOf
import it.battagliandrea.formula1.core.test.MainDispatcherRule
import it.battagliandrea.formula1.data.results.api.repository.IResultsRepository
import it.battagliandrea.formula1.data.results.impl.datasource.ErgastApiContract
import it.battagliandrea.formula1.data.results.impl.models.MockUtils
import it.battagliandrea.formula1.data.results.impl.models.tables.BaseResponse
import it.battagliandrea.formula1.data.results.impl.repository.ResultsRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@RunWith(JUnit4::class)
class ResultsRepositoryTest {

    private lateinit var repository: IResultsRepository
    private var apiContract: ErgastApiContract = mock()

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

        whenever(apiContract.results(year = 2023, round = 20, limit = 20, offset = 0))
            .thenReturn(
                ApiResponse.responseOf {
                    Response.success(
                        mockData,
                    )
                },
            )

        repository.getResults(
            year = 2023,
            round = 20,
            limit = 20,
            offset = 0,
            onStart = {},
            onComplete = {},
            onError = {},
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            val expectItem = awaitItem()
            assertEquals(expectItem.limit, 30)
            assertEquals(expectItem.offset, 0)
            assertEquals(expectItem.total, 20)
            assertEquals(expectItem.data.first().season, 2023)
            assertEquals(expectItem.data.first().round, 20)
            assertEquals(expectItem.data.first().circuit.id, "interlagos")
            assertTrue(expectItem.data.first().results.isNotEmpty())
            assertEquals(expectItem.data.first().results.first().driver.id, "max_verstappen")
            assertEquals(expectItem.data.first().results.first().constructor.id, "red_bull")
            awaitComplete()
        }

        verify(apiContract, atLeastOnce()).results(year = 2023, round = 20, limit = 20, offset = 0)
    }

    @Test
    fun getCurrentLastResultFromNetworkTest() = runTest {
        val mockData = BaseResponse(
            mRData = MockUtils.mockDataRaceTableDto(),
        )

        whenever(apiContract.currentLastResults())
            .thenReturn(
                ApiResponse.responseOf {
                    Response.success(
                        mockData,
                    )
                },
            )

        repository.getCurrentLastResult(
            onStart = {},
            onComplete = {},
            onError = {},
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            val expectItem = awaitItem()
            assertEquals(expectItem.limit, 30)
            assertEquals(expectItem.offset, 0)
            assertEquals(expectItem.total, 20)
            assertEquals(expectItem.data.first().season, 2023)
            assertEquals(expectItem.data.first().round, 20)
            assertEquals(expectItem.data.first().circuit.id, "interlagos")
            assertTrue(expectItem.data.first().results.isNotEmpty())
            assertEquals(expectItem.data.first().results.first().driver.id, "max_verstappen")
            assertEquals(expectItem.data.first().results.first().constructor.id, "red_bull")
            awaitComplete()
        }

        verify(apiContract, atLeastOnce()).currentLastResults()
    }
}
