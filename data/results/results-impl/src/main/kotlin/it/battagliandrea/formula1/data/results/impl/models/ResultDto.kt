package it.battagliandrea.formula1.data.results.impl.models

import it.battagliandrea.formula1.domain.models.Result
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultDto(
    @SerialName("number")
    val number: Int? = null,
    @SerialName("position")
    val position: Int? = null,
    @SerialName("positionText")
    val positionText: String? = null,
    @SerialName("points")
    val points: Int? = null,
    @SerialName("Driver")
    val driver: DriverDto? = null,
    @SerialName("Constructor")
    val constructor: ConstructorDto? = null,
    @SerialName("grid")
    val grid: Int? = null,
    @SerialName("laps")
    val laps: Int? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("Time")
    val time: TimeDto? = null,
    @SerialName("FastestLap")
    val fastestLap: FastestLapDto? = null,
)

fun ResultDto?.mapToDomain(): Result =
    Result(
        number = this?.number ?: 0,
        position = this?.position ?: 0,
        points = this?.points ?: 0,
        driver = this?.driver.mapToDomain(),
        constructor = this?.constructor.mapToDomain(),
        grid = this?.grid ?: 0,
        laps = this?.laps ?: 0,
        status = this?.status.orEmpty(),
        time = this?.time.mapToDomain(),
        fastestLap = this?.fastestLap.mapToDomain(),
    )
