package it.battagliandrea.formula1.domain.models

import kotlinx.datetime.LocalDate

data class Driver(
    val id: String,
    val permanentNumber: Int,
    val code: String,
    val name: String,
    val lastName: String,
    val dateOfBirth: LocalDate?,
    val nationality: String,
    val url: String = String(),
)
