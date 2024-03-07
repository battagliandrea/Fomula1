package it.battagliandrea.formula1.data.races.impl.models

import it.battagliandrea.formula1.domain.models.LapTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LapTimeDto(
    @SerialName("time")
    val time: String? = null,
)

fun LapTimeDto?.mapToDomain(): LapTime =
    LapTime(
        time = this?.time.orEmpty(),
    )
