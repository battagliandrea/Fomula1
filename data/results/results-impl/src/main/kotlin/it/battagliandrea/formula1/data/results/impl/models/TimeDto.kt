package it.battagliandrea.formula1.data.results.impl.models

import it.battagliandrea.formula1.domain.models.Location
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TimeDto(
    @SerialName("millis")
    val millis: String? = null,
    @SerialName("time")
    val time: String? = null,
)

fun LocationDto?.mapToDomain(): Location =
    Location(
        lat = this?.lat ?: 0.0,
        lng = this?.long ?: 0.0,
        locality = this?.locality.orEmpty(),
        country = this?.country.orEmpty(),
    )
