package it.battagliandrea.formula1.data.results.impl.models

import it.battagliandrea.formula1.domain.models.Driver
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DriverDto(
    @SerialName("driverId")
    val driverId: String? = null,
    @SerialName("permanentNumber")
    val permanentNumber: Int? = null,
    @SerialName("code")
    val code: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("givenName")
    val givenName: String? = null,
    @SerialName("familyName")
    val familyName: String? = null,
    @SerialName("dateOfBirth")
    val dateOfBirth: String? = null,
    @SerialName("nationality")
    val nationality: String? = null,
)

fun DriverDto?.mapToDomain(): Driver =
    Driver(
        id = this?.driverId.orEmpty(),
        permanentNumber = this?.permanentNumber ?: 0,
        code = this?.code.orEmpty(),
        name = this?.givenName.orEmpty(),
        lastName = this?.familyName.orEmpty(),
        dateOfBirth = this?.dateOfBirth.orEmpty(),
        nationality = this?.nationality.orEmpty(),
        url = this?.url.orEmpty(),
    )