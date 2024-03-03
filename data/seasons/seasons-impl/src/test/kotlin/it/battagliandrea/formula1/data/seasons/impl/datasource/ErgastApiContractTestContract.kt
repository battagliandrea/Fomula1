package it.battagliandrea.formula1.data.seasons.impl.datasource

import it.battagliandrea.formula1.core.network.test.ApiContractAbstract
import it.battagliandrea.formula1.data.seasons.impl.models.tables.DataSeasonsTableDto
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
    fun seasonsTest() = runTest {
        enqueueResponse("seasons.json")

        val response = apiContract.seasons(limit = 20, offset = 0)
        require(response.isRight())

        with(requireNotNull(response.getOrNull())) {
            assertThat(mRData, instanceOf(DataSeasonsTableDto::class.java))
            assertThat(mRData?.total, `is`(75))
            assertThat(mRData?.offset, `is`(0))
            assertThat(mRData?.limit, `is`(100))
            assert(mRData?.seasonTable!!.seasons!!.isNotEmpty())
        }
    }
}
