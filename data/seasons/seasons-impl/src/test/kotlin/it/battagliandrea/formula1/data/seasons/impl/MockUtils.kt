package it.battagliandrea.formula1.data.seasons.impl

import it.battagliandrea.formula1.data.seasons.impl.models.SeasonDto
import it.battagliandrea.formula1.data.seasons.impl.models.tables.DataSeasonsTableDto
import it.battagliandrea.formula1.data.seasons.impl.models.tables.SeasonTableDto

internal object MockUtils {

    fun mockDataSeasonsTableDto() = DataSeasonsTableDto(
        limit = 30,
        offset = 0,
        total = 20,
        seasonTable = mockSeasonTableDto(),
    )

    fun mockSeasonTableDto() = SeasonTableDto(
        seasons = mockSeasonsDtoList(),
    )

    fun mockSeasonsDtoList() = listOf(mockSeasonDto())

    fun mockSeasonDto() = SeasonDto(
        season = "2024",
        url = "https://en.wikipedia.org/wiki/2024_Formula_One_World_Championship",
    )
}
