package it.battagliandrea.formula1.domain.models

data class FastestLap(
    val rank: Int,
    val lap: Int,
    val time: Time,
    val averageSpeed: AverageSpeed,
)
