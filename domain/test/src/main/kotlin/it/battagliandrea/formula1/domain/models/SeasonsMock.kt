package it.battagliandrea.formula1.domain.models

object SeasonsMock {

    fun mockQueryResult() = QueryResult(
        limit = 30,
        offset = 0,
        total = 20,
        data = mockSeasonsList(),
    )

    fun mockSeasonsList() = listOf(mockSeason())

    fun mockSeason() = Season(
        year = 2024,
        url = "https://en.wikipedia.org/wiki/2024_Formula_One_World_Championship",
    )
}
