package it.battagliandrea.formula1.domain.models

data class Podium(
    val first: Result?,
    val second: Result?,
    var third: Result?,
)