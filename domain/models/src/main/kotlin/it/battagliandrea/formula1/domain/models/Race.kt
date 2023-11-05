package it.battagliandrea.formula1.domain.models

data class Race(
    val season: Long,
    val round: Int,
    val name: String,
    val circuit: Circuit,
    val date: String,
    val time: String,
    val results: List<Result> = emptyList(),
    val url: String = String(),
)
