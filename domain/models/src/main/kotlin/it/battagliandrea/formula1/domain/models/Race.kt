package it.battagliandrea.formula1.domain.models

import it.battagliandrea.formula1.domain.models.Schedule.*

data class Race(
    val season: Long,
    val round: Int,
    val name: String,
    val circuit: Circuit,
    val schedules: Map<SessionType, Schedule>,
    val results: List<Result> = emptyList(),
    val url: String = String(),
)
