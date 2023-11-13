package it.battagliandrea.formula1.data.results.impl.models

import it.battagliandrea.formula1.core.datetime.models.RaceDuration
import it.battagliandrea.formula1.domain.models.RaceTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RaceTimeDto(
    @SerialName("millis")
    val millis: Long? = null,
    @SerialName("time")
    val time: String? = null,
)

fun RaceTimeDto.mapToDomain(): RaceTime =
    RaceTime(
        duration = RaceDuration.fromMillisecond(this.millis),
        time = this.time.orEmpty(),
    )
