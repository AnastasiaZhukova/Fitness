package com.github.anastasiazhukova.fitness.screens.fragments.statistics.datasource.weight

import com.github.anastasiazhukova.fitness.screens.fragments.statistics.domain.StatisticsEntry

class StubWeightStatisticsRepository : IWeightStatisticsRepository {
    override suspend fun load(): List<StatisticsEntry> {
        return listOf(
            StatisticsEntry(1, 48.0f),
            StatisticsEntry(2, 48.3f),
            StatisticsEntry(3, 48.3f),
            StatisticsEntry(4, 48.5f),
            StatisticsEntry(5, 48.8f),
            StatisticsEntry(6, 49.0f),
            StatisticsEntry(7, 49.1f)
        )
    }
}