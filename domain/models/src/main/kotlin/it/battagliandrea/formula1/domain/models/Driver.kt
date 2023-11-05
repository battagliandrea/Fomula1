package it.battagliandrea.formula1.domain.models

data class Driver(
    val id: String,
    val permanentNumber: Int,
    val code: String,
    val name: String,
    val lastName: String,
    val dateOfBirth: String,
    val nationality: String,
    val url: String = String(),
)
