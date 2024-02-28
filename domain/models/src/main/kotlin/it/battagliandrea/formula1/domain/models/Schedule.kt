package it.battagliandrea.formula1.domain.models

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class Schedule (
    val type: Type,
    val date: LocalDate,
    val time: LocalTime,
) {
    enum class Type {
        RACE,
        SPRINT,
        QUALIFYING,
        FIRST_PRACTICE,
        SECOND_PRACTICE,
        THIRD_PRACTICE
    }
}