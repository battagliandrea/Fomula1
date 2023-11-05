package it.battagliandrea.formula1.data.results.impl.models

import it.battagliandrea.formula1.domain.models.Circuit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CircuitDto(
    @SerialName("circuitId")
    val circuitId: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("circuitName")
    val circuitName: String? = null,
    @SerialName("Location")
    val location: LocationDto? = null,
)

fun CircuitDto?.mapToDomain(): Circuit =
    Circuit(
        id = this?.circuitId.orEmpty(),
        name = this?.circuitName.orEmpty(),
        location = this?.location.mapToDomain(),
        url = this?.circuitId.orEmpty(),
    )
