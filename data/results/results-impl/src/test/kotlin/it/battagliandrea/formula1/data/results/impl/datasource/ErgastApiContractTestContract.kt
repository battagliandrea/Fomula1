package it.battagliandrea.formula1.data.results.impl.datasource

import it.battagliandrea.formula1.core.network.test.ApiContractAbstract
import it.battagliandrea.formula1.data.results.impl.models.tables.DataRaceTableDto
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ErgastApiContractTestContract : ApiContractAbstract<ErgastApiContract>() {

    private lateinit var apiContract: ErgastApiContract

    @Before
    fun setup() {
        apiContract = createService(ErgastApiContract::class.java)
    }

    @Test
    fun currentScheduleTest() = runTest {
        enqueueResponse("schedules/current.json")

        val response = apiContract.currentLastResults()
        require(response.isRight())

        with(requireNotNull(response.getOrNull())) {
            assertThat(mRData, instanceOf(DataRaceTableDto::class.java))
            assertThat(mRData?.total, `is`(24))
            assertThat(mRData?.offset, `is`(0))
            assertThat(mRData?.limit, `is`(30))
            assert(mRData?.raceTable!!.races!!.isNotEmpty())
            assertNull(mRData?.raceTable!!.races!!.first().results)
            assertNotNull(mRData?.raceTable!!.races!!.first().firstPractice)
            assertNotNull(mRData?.raceTable!!.races!!.first().secondPractice)
            assertNotNull(mRData?.raceTable!!.races!!.first().thirdPractice)
            assertNotNull(mRData?.raceTable!!.races!!.first().qualifying)
            assertNotNull(mRData?.raceTable!!.races!!.first().date)
            assertNotNull(mRData?.raceTable!!.races!!.first().time)
        }
    }

    @Test
    fun resultsTest() = runTest {
        enqueueResponse("results.json")

        val response = apiContract.currentLastResults()
        require(response.isRight())

        with(requireNotNull(response.getOrNull())) {
            assertThat(mRData, instanceOf(DataRaceTableDto::class.java))
            assertThat(mRData?.total, `is`(20))
            assertThat(mRData?.offset, `is`(2))
            assertThat(mRData?.limit, `is`(10))
            assert(mRData?.raceTable!!.races!!.isNotEmpty())
        }
    }

    @Test
    fun currentLastResultsTest() = runTest {
        enqueueResponse("current_last.json")

        val response = apiContract.currentLastResults()
        with(requireNotNull(response.getOrNull())) {
            assertThat(mRData, instanceOf(DataRaceTableDto::class.java))
            assertThat(mRData?.total, `is`(20))
            assert(mRData?.raceTable!!.races!!.isNotEmpty())
        }
    }
}
