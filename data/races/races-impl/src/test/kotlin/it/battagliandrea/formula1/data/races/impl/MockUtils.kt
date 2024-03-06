package it.battagliandrea.formula1.data.races.impl

import it.battagliandrea.formula1.data.races.impl.models.AverageSpeedDto
import it.battagliandrea.formula1.data.races.impl.models.CircuitDto
import it.battagliandrea.formula1.data.races.impl.models.ConstructorDto
import it.battagliandrea.formula1.data.races.impl.models.DriverDto
import it.battagliandrea.formula1.data.races.impl.models.FastestLapDto
import it.battagliandrea.formula1.data.races.impl.models.LapTimeDto
import it.battagliandrea.formula1.data.races.impl.models.LocationDto
import it.battagliandrea.formula1.data.races.impl.models.RaceDto
import it.battagliandrea.formula1.data.races.impl.models.RaceTimeDto
import it.battagliandrea.formula1.data.races.impl.models.ResultDto
import it.battagliandrea.formula1.data.races.impl.models.ScheduleDto
import it.battagliandrea.formula1.data.races.impl.models.tables.DataRaceTableDto
import it.battagliandrea.formula1.data.races.impl.models.tables.RaceTableDto

internal object MockUtils {

    fun mockDataRaceTableDto() = DataRaceTableDto(
        limit = 30,
        offset = 0,
        total = 20,
        raceTable = mockRaceTableDto(),
    )

    fun mockRaceTableDto() = RaceTableDto(
        season = 2023,
        round = 20,
        races = mockRaceDtoList(),
    )

    fun mockRaceDtoList() = listOf(mockRaceDto())

    fun mockRaceDto() = RaceDto(
        season = 2023,
        round = 20,
        url = "https://en.wikipedia.org/wiki/2023_S%C3%A3o_Paulo_Grand_Prix",
        raceName = "São Paulo Grand Prix",
        circuit = mockCircuit(),
        date = "2023-11-05",
        time = "17:00:00Z",
        firstPractice = mockSchedule(),
        secondPractice = mockSchedule(),
        thirdPractice = mockSchedule(),
        qualifying = mockSchedule(),
        results = mockResultList(),
    )

    fun mockSchedule() =
        ScheduleDto(
            date = "2023-11-05",
            time = "17:00:00Z",
        )

    fun mockCircuit() =
        CircuitDto(
            circuitId = "interlagos",
            circuitName = "Autódromo José Carlos Pace",
            url = "http://en.wikipedia.org/wiki/Aut%C3%B3dromo_Jos%C3%A9_Carlos_Pace",
            location = mockLocation(),
        )

    fun mockLocation() =
        LocationDto(
            lat = -23.7036,
            long = -46.6997,
            locality = "São Paulo",
            country = "Brazil",
        )

    fun mockResultList() = listOf(mockResult())

    fun mockResult() =
        ResultDto(
            number = 1,
            position = 1,
            positionText = "1",
            points = 25,
            driver = mockDriver(),
            constructor = mockConstructor(),
            grid = 1,
            laps = 71,
            status = "Finished",
            time = mockRaceTime(),
            fastestLap = mockFastestLap(),
        )

    fun mockDriver() =
        DriverDto(
            driverId = "max_verstappen",
            permanentNumber = 33,
            code = "VER",
            givenName = "Max",
            familyName = "Verstappen",
            dateOfBirth = "1997-09-30",
            nationality = "Dutch",
            url = "http://en.wikipedia.org/wiki/Max_Verstappen",
        )

    fun mockConstructor() =
        ConstructorDto(
            constructorId = "red_bull",
            name = "Red Bull",
            nationality = "Austrian",
            url = "http://en.wikipedia.org/wiki/Red_Bull_Racing",
        )

    fun mockRaceTime() =
        RaceTimeDto(
            millis = 7008894,
            time = "1:56:48.894",
        )

    fun mockFastestLap() =
        FastestLapDto(
            rank = 2,
            lap = 68,
            time = mockLapTime(),
            averageSpeed = mockAverageSpeed(),
        )

    fun mockLapTime() =
        LapTimeDto(
            time = "1:13.422",
        )

    fun mockAverageSpeed() =
        AverageSpeedDto(
            units = "kph",
            speed = "211.277",
        )
}
