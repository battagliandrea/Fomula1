package it.battagliandrea.formula1.data.results.impl.models

import it.battagliandrea.formula1.domain.models.FastestLap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FastestLapDto(
    @SerialName("rank")
    val rank: Int? = null,
    @SerialName("lap")
    val lap: Int? = null,
    @SerialName("time")
    val time: LapTimeDto? = null,
    @SerialName("AverageSpeed")
    val averageSpeed: AverageSpeedDto? = null,
)

fun FastestLapDto.mapToDomain(): FastestLap =
    FastestLap(
        rank = this.rank ?: 0,
        lap = this.lap ?: 0,
        time = this.time.mapToDomain(),
        averageSpeed = this.averageSpeed.mapToDomain(),
    )
