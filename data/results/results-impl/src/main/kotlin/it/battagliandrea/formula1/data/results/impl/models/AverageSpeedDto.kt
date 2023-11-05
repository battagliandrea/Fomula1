package it.battagliandrea.formula1.data.results.impl.models

import it.battagliandrea.formula1.domain.models.AverageSpeed
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AverageSpeedDto(
    @SerialName("units")
    val units: String? = null,
    @SerialName("speed")
    val speed: String? = null,
)

fun AverageSpeedDto?.mapToDomain(): AverageSpeed =
    AverageSpeed(
        units = this?.units.orEmpty(),
        speed = this?.speed.orEmpty(),
    )
