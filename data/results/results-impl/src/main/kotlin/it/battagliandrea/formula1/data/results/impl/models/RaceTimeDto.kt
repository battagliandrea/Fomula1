package it.battagliandrea.formula1.data.results.impl.models

import it.battagliandrea.formula1.domain.models.RaceTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RaceTimeDto(
    @SerialName("millis")
    val millis: String? = null,
    @SerialName("time")
    val time: String? = null,
)

fun RaceTimeDto.mapToDomain(): RaceTime =
    RaceTime(
        millis = this.millis.orEmpty(),
        time = this.time.orEmpty(),
    )
