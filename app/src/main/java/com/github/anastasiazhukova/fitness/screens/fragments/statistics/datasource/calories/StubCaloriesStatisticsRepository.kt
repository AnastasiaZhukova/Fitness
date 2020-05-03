package com.github.anastasiazhukova.fitness.screens.fragments.statistics.datasource.calories

import com.github.anastasiazhukova.fitness.screens.fragments.statistics.domain.StatisticsEntry

class StubCaloriesStatisticsRepository : ICaloriesStatisticsRepository {
    override suspend fun load(): List<StatisticsEntry> {
        return listOf(
            StatisticsEntry(1, 1050.0f),
            StatisticsEntry(2, 1200.0f),
            StatisticsEntry(3, 1000.0f),
            StatisticsEntry(4, 960.0f),
            StatisticsEntry(5, 1300.0f),
            StatisticsEntry(6, 1350.0f),
            StatisticsEntry(7, 1150.0f)
        )
    }
}