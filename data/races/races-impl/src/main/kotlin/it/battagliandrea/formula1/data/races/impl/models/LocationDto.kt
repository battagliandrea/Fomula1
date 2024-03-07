package it.battagliandrea.formula1.data.races.impl.models

import it.battagliandrea.formula1.domain.models.Location
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

fun LocationDto?.mapToDomain(): Location =
    Location(
        lat = this?.lat ?: 0.0,
        lng = this?.long ?: 0.0,
        locality = this?.locality.orEmpty(),
        country = this?.country.orEmpty(),
    )
