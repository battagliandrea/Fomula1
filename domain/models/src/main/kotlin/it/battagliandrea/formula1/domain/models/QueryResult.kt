package it.battagliandrea.formula1.domain.models

data class QueryResult<out T>(
    val limit: Long,
    val offset: Long,
    val total: Long,
    val data: T,
)
