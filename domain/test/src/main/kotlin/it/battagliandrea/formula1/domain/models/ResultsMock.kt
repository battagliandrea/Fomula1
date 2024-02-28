package it.battagliandrea.formula1.domain.models

import it.battagliandrea.formula1.core.datetime.models.RaceDuration
import it.battagliandrea.formula1.core.datetime.toRaceTime
import it.battagliandrea.formula1.domain.models.Schedule.Type.RACE
import kotlinx.datetime.toLocalDate

object ResultsMock {

    fun mockQueryResult() = QueryResult(
        limit = 30,
        offset = 0,
        total = 20,
        data = mockRaceList(),
    )

    fun mockRaceList() = listOf(mockRace())

    fun mockRace() = Race(
        season = 2023,
        round = 20,
        name = "São Paulo Grand Prix",
        circuit = mockCircuit(),
        schedules = listOf(
            Schedule(
                type = RACE,
                date = "2023-11-05".toLocalDate(),
                time = "17:00:00Z".toRaceTime(),
            )
        ),
        results = mockResultList(),
        url = "https://en.wikipedia.org/wiki/2023_S%C3%A3o_Paulo_Grand_Prix",
    )

    fun mockCircuit() =
        Circuit(
            id = "interlagos",
            name = "Autódromo José Carlos Pace",
            location = mockLocation(),
        )

    fun mockLocation() =
        Location(
            lat = -23.7036,
            lng = -46.6997,
            locality = "São Paulo",
            country = "Brazil",
        )

    fun mockResultList() = listOf(mockResult())

    fun mockResult() =
        Result(
            number = 1,
            position = 1,
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
        Driver(
            id = "max_verstappen",
            permanentNumber = 33,
            code = "VER",
            name = "Max",
            lastName = "Verstappen",
            dateOfBirth = "1997-09-30".toLocalDate(),
            nationality = "Dutch",
            url = "http://en.wikipedia.org/wiki/Max_Verstappen",
        )

    fun mockConstructor() =
        Constructor(
            id = "red_bull",
            name = "Red Bull",
            nationality = "Austrian",
            url = "http://en.wikipedia.org/wiki/Red_Bull_Racing",
        )

    fun mockRaceTime() =
        RaceTime(
            duration = RaceDuration.fromMillisecond(7008894),
            time = "1:56:48.894",
        )

    fun mockFastestLap() =
        FastestLap(
            rank = 2,
            lap = 68,
            time = mockLapTime(),
            averageSpeed = mockAverageSpeed(),
        )

    fun mockLapTime() =
        LapTime(
            time = "1:13.422",
        )

    fun mockAverageSpeed() =
        AverageSpeed(
            units = "kph",
            speed = "211.277",
        )
}
