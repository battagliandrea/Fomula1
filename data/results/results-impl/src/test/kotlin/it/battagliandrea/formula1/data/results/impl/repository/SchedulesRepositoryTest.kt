package it.battagliandrea.formula1.data.results.impl.repository

import app.cash.turbine.test
import arrow.core.left
import arrow.core.right
import arrow.retrofit.adapter.either.networkhandling.HttpError
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import it.battagliandrea.formula1.core.network.api.models.BaseResponse
import it.battagliandrea.formula1.core.test.MainDispatcherRule
import it.battagliandrea.formula1.data.results.api.repository.ISchedulesRepository
import it.battagliandrea.formula1.data.results.impl.MockUtils
import it.battagliandrea.formula1.data.results.impl.datasource.ErgastApiContract
import it.battagliandrea.formula1.domain.models.ErrorType
import it.battagliandrea.formula1.domain.models.SessionType.FIRST_PRACTICE
import it.battagliandrea.formula1.domain.models.SessionType.QUALIFYING
import it.battagliandrea.formula1.domain.models.SessionType.RACE
import it.battagliandrea.formula1.domain.models.SessionType.SECOND_PRACTICE
import it.battagliandrea.formula1.domain.models.SessionType.SPRINT
import it.battagliandrea.formula1.domain.models.SessionType.THIRD_PRACTICE
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.RuntimeException
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@RunWith(JUnit4::class)
class SchedulesRepositoryTest {

    private lateinit var repository: ISchedulesRepository
    private var apiContract: ErgastApiContract = mockk()

    @get:Rule
    val coroutinesRule = MainDispatcherRule()

    @Before
    fun setup() {
        repository = SchedulesRepository(
            apiContract = apiContract,
            ioDispatcher = coroutinesRule.testDispatcher,
        )
    }

    @Test
    fun whenCurrentScheduleIsSuccess_thenShouldBeAListOfValidRaces() = runTest {
        val mockData = BaseResponse(
            mRData = MockUtils.mockDataRaceTableDto(),
        )

        coEvery { apiContract.currentSchedule() } returns(mockData.right())

        repository.getCurrentSchedule().test(2.toDuration(DurationUnit.SECONDS)) {
            val expectItem = awaitItem()
            require(expectItem.isRight())

            with(expectItem.getOrNull()?.first()) {
                assertEquals(this?.season, 2023L)
                assertEquals(this?.round, 20)
                assertEquals(this?.circuit?.id, "interlagos")
                requireNotNull(this?.schedules?.get(FIRST_PRACTICE))
                requireNotNull(this?.schedules?.get(SECOND_PRACTICE))
                requireNotNull(this?.schedules?.get(THIRD_PRACTICE))
                requireNotNull(this?.schedules?.get(QUALIFYING))
                requireNotNull(this?.schedules?.get(RACE))
                assertNull(this?.schedules?.get(SPRINT))
            }

            awaitComplete()
        }

        coVerify(atLeast = 1) { apiContract.currentSchedule() }
    }

    @Test
    fun whenCurrentScheduleResponseWith404_thenShouldReturnNotFoundError() = runTest {
        coEvery { apiContract.currentSchedule() } returns(
            HttpError(code = 404, message = "Not Found", body = "{}").left()
            )

        repository.getCurrentSchedule().test(2.toDuration(DurationUnit.SECONDS)) {
            val expectItem = awaitItem()
            require(expectItem.isLeft())
            require(expectItem.leftOrNull() is ErrorType.Api.NotFound)

            awaitComplete()
        }

        coVerify(atLeast = 1) { apiContract.currentSchedule() }
    }

    @Test
    fun whenCurrentScheduleResponseWith500_thenShouldReturnServerError() = runTest {
        coEvery { apiContract.currentSchedule() } returns(
            HttpError(code = 500, message = "Server Error", body = "{}").left()
            )

        repository.getCurrentSchedule().test(2.toDuration(DurationUnit.SECONDS)) {
            val expectItem = awaitItem()
            require(expectItem.isLeft())
            require(expectItem.leftOrNull() is ErrorType.Api.Server)

            awaitComplete()
        }

        coVerify(atLeast = 1) { apiContract.currentSchedule() }
    }

    @Test
    fun whenCurrentScheduleThrowAnException_thenShouldReturnUnknownError() = runTest {
        coEvery { apiContract.currentSchedule() } throws RuntimeException()

        repository.getCurrentSchedule().test(2.toDuration(DurationUnit.SECONDS)) {
            val expectItem = awaitItem()
            require(expectItem.isLeft())
            require(expectItem.leftOrNull() is ErrorType.Unknown)

            awaitComplete()
        }

        coVerify(atLeast = 1) { apiContract.currentSchedule() }
    }
}
