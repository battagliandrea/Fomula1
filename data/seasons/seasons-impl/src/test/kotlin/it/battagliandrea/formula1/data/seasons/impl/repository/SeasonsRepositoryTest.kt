package it.battagliandrea.formula1.data.seasons.impl.repository

import app.cash.turbine.test
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.responseOf
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import it.battagliandrea.formula1.core.network.api.models.BaseResponse
import it.battagliandrea.formula1.core.resource.Resource
import it.battagliandrea.formula1.core.test.MainDispatcherRule
import it.battagliandrea.formula1.data.seasons.api.ISeasonsRepository
import it.battagliandrea.formula1.data.seasons.impl.MockUtils
import it.battagliandrea.formula1.data.seasons.impl.datasource.ErgastApiContract
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@RunWith(JUnit4::class)
class SeasonsRepositoryTest {

    private lateinit var repository: ISeasonsRepository
    private var apiContract: ErgastApiContract = mockk()

    @get:Rule
    val coroutinesRule = MainDispatcherRule()

    @Before
    fun setup() {
        repository = SeasonsRepository(
            apiContract = apiContract,
            ioDispatcher = coroutinesRule.testDispatcher,
        )
    }

    @Test
    fun getSeasonsFromNetworkTest() = runTest {
        val mockData = BaseResponse(
            mRData = MockUtils.mockDataSeasonsTableDto(),
        )

        coEvery { apiContract.seasons(limit = any(), offset = any()) } returns(
            ApiResponse.responseOf {
                Response.success(
                    mockData,
                )
            }
            )

        repository.getSeasons().test(2.toDuration(DurationUnit.SECONDS)) {
            val expectItem = awaitItem()
            require(expectItem is Resource.Success)
            assertEquals(expectItem.data.first().year, 2024)

            awaitComplete()
        }

        coVerify(atLeast = 1) { apiContract.seasons(limit = any(), offset = any()) }
    }
}
