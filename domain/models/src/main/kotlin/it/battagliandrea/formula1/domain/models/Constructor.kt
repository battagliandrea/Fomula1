package it.battagliandrea.formula1.domain.models

data class Constructor(
    val id: String,
    val name: String,
    val nationality: String,
    val url: String = String(),
)