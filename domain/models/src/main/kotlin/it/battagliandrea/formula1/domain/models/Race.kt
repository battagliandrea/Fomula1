package it.battagliandrea.formula1.domain.models

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class Race(
    val season: Long,
    val round: Int,
    val name: String,
    val circuit: Circuit,
    val date: LocalDate?,
    val time: LocalTime?,
    val results: List<Result> = emptyList(),
    val url: String = String(),
)
