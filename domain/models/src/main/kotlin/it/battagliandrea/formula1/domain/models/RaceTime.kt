package it.battagliandrea.formula1.domain.models

import it.battagliandrea.formula1.core.datetime.models.RaceDuration

data class RaceTime(
    val duration: RaceDuration,
    val time: String,
)
