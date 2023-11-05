package it.battagliandrea.formula1.domain.models

data class Circuit(
    val id: String,
    val name: String,
    val location: Location? = null,
    val url: String = String(),
)
