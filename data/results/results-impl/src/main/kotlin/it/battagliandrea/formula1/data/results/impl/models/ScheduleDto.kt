package it.battagliandrea.formula1.data.results.impl.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleDto(
    @SerialName("date")
    val date: String? = null,
    @SerialName("time")
    val time: String? = null,
)
