package it.battagliandrea.formula1.data.results.impl

import com.skydoves.sandwich.ApiResponse
import it.battagliandrea.formula1.core.network.test.ApiContractAbstract
import it.battagliandrea.formula1.data.results.impl.datasource.ErgastApiContract
import it.battagliandrea.formula1.data.results.impl.models.tables.DataRaceTableDto
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
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
    fun resultsTest() = runTest {
        enqueueResponse("results.json")

        val response = apiContract.currentLastResults()
        val responseBody = requireNotNull((response as ApiResponse.Success).data)

        assertThat(responseBody.mRData, instanceOf(DataRaceTableDto::class.java))

        with(responseBody.mRData as DataRaceTableDto) {
            assertThat(total, `is`(20))
            assertThat(offset, `is`(2))
            assertThat(limit, `is`(10))
            assert(raceTable!!.races!!.isNotEmpty())
        }
    }

    @Test
    fun currentLastResultsTest() = runTest {
        enqueueResponse("current_last.json")

        val response = apiContract.currentLastResults()
        val responseBody = requireNotNull((response as ApiResponse.Success).data)

        assertThat(responseBody.mRData, instanceOf(DataRaceTableDto::class.java))

        with(responseBody.mRData as DataRaceTableDto) {
            assertThat(total, `is`(20))
            assert(raceTable!!.races!!.isNotEmpty())
        }
    }
}
