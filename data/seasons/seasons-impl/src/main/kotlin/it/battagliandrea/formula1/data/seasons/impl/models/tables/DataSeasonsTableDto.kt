package it.battagliandrea.formula1.data.seasons.impl.models.tables

import it.battagliandrea.formula1.core.network.api.models.DataDto
import it.battagliandrea.formula1.data.seasons.impl.models.SeasonDto
import it.battagliandrea.formula1.data.seasons.impl.models.mapToDomain
import it.battagliandrea.formula1.domain.models.QueryResult
import it.battagliandrea.formula1.domain.models.Season
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataSeasonsTableDto(
    @SerialName("limit")
    override val limit: Long? = null,
    @SerialName("offset")
    override val offset: Long? = null,
    @SerialName("total")
    override val total: Long? = null,
    @SerialName("SeasonTable")
    val seasonTable: SeasonTableDto? = null,
) : DataDto()

@Serializable
data class SeasonTableDto(
    @SerialName("Seasons")
    val seasons: List<SeasonDto?>? = null,
)

fun DataSeasonsTableDto?.mapToDomain(): QueryResult<List<Season>> =
    QueryResult(
        limit = this?.limit ?: 0,
        offset = this?.offset ?: 0,
        total = this?.total ?: 0,
        data = this?.seasonTable?.seasons?.map { season ->
            season.mapToDomain()
        }.orEmpty(),
    )
