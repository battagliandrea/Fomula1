package it.battagliandrea.formula1.data.results.impl.models.tables

import it.battagliandrea.formula1.core.network.api.models.DataDto
import it.battagliandrea.formula1.data.results.impl.models.RaceDto
import it.battagliandrea.formula1.data.results.impl.models.mapToDomain
import it.battagliandrea.formula1.domain.models.QueryResult
import it.battagliandrea.formula1.domain.models.Race
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataRaceTableDto(
    @SerialName("limit")
    override val limit: Long? = null,
    @SerialName("offset")
    override val offset: Long? = null,
    @SerialName("total")
    override val total: Long? = null,
    @SerialName("RaceTable")
    val raceTable: RaceTableDto? = null,
) : DataDto()

@Serializable
data class RaceTableDto(
    @SerialName("season")
    val season: Long? = null,
    @SerialName("round")
    val round: Int? = null,
    @SerialName("Races")
    val races: List<RaceDto>? = null,
)

fun DataRaceTableDto?.mapToDomain(): QueryResult<List<Race>> =
    QueryResult(
        limit = this?.limit ?: 0,
        offset = this?.offset ?: 0,
        total = this?.total ?: 0,
        data = this?.raceTable?.races?.map { race ->
            race.mapToDomain()
        }.orEmpty(),
    )
