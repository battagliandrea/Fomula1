package it.battagliandrea.formula1.domain.models

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class Schedule (
    val date: LocalDate,
    val time: LocalTime,
)