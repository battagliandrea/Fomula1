package it.battagliandrea.formula1.domain.models

data class Location(
    val lat: Double,
    val lng: Double,
    val locality: String,
    val country: String,
)
