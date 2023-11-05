package it.battagliandrea.formula1.data.results.impl.models

import it.battagliandrea.formula1.domain.models.Time
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    @SerialName("lat")
    val lat: Double? = null,
    @SerialName("long")
    val long: Double? = null,
    @SerialName("locality")
    val locality: String? = null,
    @SerialName("country")
    val country: String? = null,
)

fun TimeDto?.mapToDomain(): Time =
    Time(
        millis = this?.millis.orEmpty(),
        time = this?.time.orEmpty(),
    )
