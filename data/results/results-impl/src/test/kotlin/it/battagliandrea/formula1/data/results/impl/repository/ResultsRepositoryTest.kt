package it.battagliandrea.formula1.data.results.impl.repository

import app.cash.turbine.test
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.responseOf
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import it.battagliandrea.formula1.core.network.api.models.BaseResponse
import it.battagliandrea.formula1.core.resource.Resource
import it.battagliandrea.formula1.core.test.MainDispatcherRule
import it.battagliandrea.formula1.data.results.api.repository.IResultsRepository
import it.battagliandrea.formula1.data.results.impl.MockUtils
import it.battagliandrea.formula1.data.results.impl.datasource.ErgastApiContract
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response
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

        coEvery { apiContract.results(year = 2023, round = 20, limit = 20, offset = 0) } returns(
            ApiResponse.responseOf {
                Response.success(
                    mockData,
                )
            }
            )

        repository.getResults(
            year = 2023,
            round = 20,
            limit = 20,
            offset = 0,
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            val expectItem = awaitItem()
            require(expectItem is Resource.Success)
            assertEquals(expectItem.data.first().season, 2023)
            assertEquals(expectItem.data.first().round, 20)
            assertEquals(expectItem.data.first().circuit.id, "interlagos")
            assertTrue(expectItem.data.first().results.isNotEmpty())
            assertEquals(expectItem.data.first().results.first().driver.id, "max_verstappen")
            assertEquals(expectItem.data.first().results.first().constructor.id, "red_bull")

            awaitComplete()
        }

        coVerify(atLeast = 1) { apiContract.results(year = 2023, round = 20, limit = 20, offset = 0) }
    }

    @Test
    fun getCurrentLastResultFromNetworkTest() = runTest {
        val mockData = BaseResponse(
            mRData = MockUtils.mockDataRaceTableDto(),
        )

        coEvery { apiContract.currentLastResults() } returns (
            ApiResponse.responseOf {
                Response.success(
                    mockData,
                )
            }
            )

        repository.getCurrentLastResult().test(2.toDuration(DurationUnit.SECONDS)) {
            val expectItem = awaitItem()

            require(expectItem is Resource.Success)
            assertEquals(expectItem.data.first().season, 2023)
            assertEquals(expectItem.data.first().round, 20)
            assertEquals(expectItem.data.first().circuit.id, "interlagos")
            assertTrue(expectItem.data.first().results.isNotEmpty())
            assertEquals(expectItem.data.first().results.first().driver.id, "max_verstappen")
            assertEquals(expectItem.data.first().results.first().constructor.id, "red_bull")

            awaitComplete()
        }

        coVerify(atLeast = 1) { apiContract.currentLastResults() }
    }
}
