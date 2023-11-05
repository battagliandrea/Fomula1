package it.battagliandrea.formula1.domain.models

data class Result(
    val number: Int,
    val position: Int,
    val points: Int,
    val driver: Driver,
    val constructor: Constructor,
    val grid: Int,
    val laps: Int,
    val status: String,
    val time: Time,
    val fastestLap: FastestLap,
)
