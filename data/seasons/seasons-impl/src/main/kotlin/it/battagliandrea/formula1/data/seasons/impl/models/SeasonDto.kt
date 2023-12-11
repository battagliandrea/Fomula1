package it.battagliandrea.formula1.data.seasons.impl.models

import it.battagliandrea.formula1.domain.models.Season
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeasonDto(
    @SerialName("season")
    val season: String? = null,
    @SerialName("url")
    val url: String? = null,
)

fun SeasonDto?.mapToDomain(): Season =
    Season(
        year = this?.season?.toIntOrNull() ?: 1950,
        url = this?.url.orEmpty(),
    )
