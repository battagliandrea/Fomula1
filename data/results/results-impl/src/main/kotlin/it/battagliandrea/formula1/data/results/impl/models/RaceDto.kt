package it.battagliandrea.formula1.data.results.impl.models

import it.battagliandrea.formula1.core.datetime.toRaceTime
import it.battagliandrea.formula1.domain.models.Race
import it.battagliandrea.formula1.domain.models.Schedule
import it.battagliandrea.formula1.domain.models.Schedule.Type.RACE
import kotlinx.datetime.toLocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RaceDto(
    @SerialName("season")
    val season: Long? = null,
    @SerialName("round")
    val round: Int? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("raceName")
    val raceName: String? = null,
    @SerialName("Circuit")
    val circuit: CircuitDto? = null,
    @SerialName("date")
    val date: String? = null,
    @SerialName("time")
    val time: String? = null,
    @SerialName("Results")
    val results: List<ResultDto>? = null,
)

fun RaceDto?.mapToDomain(): Race =
    Race(
        season = this?.season ?: 0,
        round = this?.round ?: 0,
        name = this?.raceName.orEmpty(),
        circuit = this?.circuit.mapToDomain(),
        schedules = this.mapToSchedules(),
        results = this?.results?.map { res ->
            res.mapToDomain()
        }.orEmpty(),
        url = this?.url.orEmpty(),
    )

fun RaceDto?.mapToSchedules(): List<Schedule> = this?.let { race ->
    val schedules = mutableListOf<Schedule>()
    if (!race.date.isNullOrEmpty() && !race.time.isNullOrEmpty()) {
        schedules.add(
            Schedule(
                type = RACE,
                date = race.date.toLocalDate(),
                time = race.time.toRaceTime(),
            ),
        )
    }
    listOf()
} ?: listOf()
