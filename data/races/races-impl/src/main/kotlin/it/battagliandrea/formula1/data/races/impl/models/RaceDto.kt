package it.battagliandrea.formula1.data.races.impl.models

import it.battagliandrea.formula1.core.datetime.toRaceTime
import it.battagliandrea.formula1.domain.models.Race
import it.battagliandrea.formula1.domain.models.Schedule
import it.battagliandrea.formula1.domain.models.SessionType
import it.battagliandrea.formula1.domain.models.SessionType.FIRST_PRACTICE
import it.battagliandrea.formula1.domain.models.SessionType.QUALIFYING
import it.battagliandrea.formula1.domain.models.SessionType.RACE
import it.battagliandrea.formula1.domain.models.SessionType.SECOND_PRACTICE
import it.battagliandrea.formula1.domain.models.SessionType.THIRD_PRACTICE
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
    @SerialName("FirstPractice")
    val firstPractice: ScheduleDto? = null,
    @SerialName("SecondPractice")
    val secondPractice: ScheduleDto? = null,
    @SerialName("ThirdPractice")
    val thirdPractice: ScheduleDto? = null,
    @SerialName("Qualifying")
    val qualifying: ScheduleDto? = null,
    @SerialName("Sprint")
    val sprint: ScheduleDto? = null,
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

fun RaceDto?.mapToSchedules(): Map<SessionType, Schedule> = this?.let { race ->
    val schedules = mutableMapOf<SessionType, Schedule>()

    if (!race.date.isNullOrEmpty() && !race.time.isNullOrEmpty()) {
        schedules[RACE] = Schedule(
            date = race.date.toLocalDate(),
            time = race.time.toRaceTime(),
        )
    }

    if (firstPractice != null && !firstPractice.date.isNullOrEmpty() && !firstPractice.time.isNullOrEmpty()) {
        schedules[FIRST_PRACTICE] = Schedule(
            date = firstPractice.date.toLocalDate(),
            time = firstPractice.time.toRaceTime(),
        )
    }

    if (secondPractice != null && !secondPractice.date.isNullOrEmpty() && !secondPractice.time.isNullOrEmpty()) {
        schedules[SECOND_PRACTICE] = Schedule(
            date = secondPractice.date.toLocalDate(),
            time = secondPractice.time.toRaceTime(),
        )
    }

    if (thirdPractice != null && !thirdPractice.date.isNullOrEmpty() && !thirdPractice.time.isNullOrEmpty()) {
        schedules[THIRD_PRACTICE] = Schedule(
            date = thirdPractice.date.toLocalDate(),
            time = thirdPractice.time.toRaceTime(),
        )
    }

    if (qualifying != null && !qualifying.date.isNullOrEmpty() && !qualifying.time.isNullOrEmpty()) {
        schedules[QUALIFYING] = Schedule(
            date = qualifying.date.toLocalDate(),
            time = qualifying.time.toRaceTime(),
        )
    }

    if (sprint != null && !sprint.date.isNullOrEmpty() && !sprint.time.isNullOrEmpty()) {
        schedules[FIRST_PRACTICE] = Schedule(
            date = sprint.date.toLocalDate(),
            time = sprint.time.toRaceTime(),
        )
    }

    schedules
} ?: mapOf()
