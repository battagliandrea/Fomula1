package it.battagliandrea.formula1.data.results.impl.models

import it.battagliandrea.formula1.domain.models.Constructor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConstructorDto(
    @SerialName("constructorId")
    val constructorId: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("nationality")
    val nationality: String? = null,
)

fun ConstructorDto?.mapToDomain(): Constructor =
    Constructor(
        id = this?.constructorId.orEmpty(),
        name = this?.name.orEmpty(),
        nationality = this?.nationality.orEmpty(),
        url = this?.url.orEmpty(),
    )
