package com.github.anastasiazhukova.statistics.datasource.weight

import com.github.anastasiazhukova.statistics.domain.StatisticsEntry

class StubWeightStatisticsRepository : IWeightStatisticsRepository {
    override suspend fun load(userId: String): List<StatisticsEntry> {
        return listOf(
            StatisticsEntry.Weight(1, 48.0f),
            StatisticsEntry.Weight(2, 48.3f),
            StatisticsEntry.Weight(3, 48.3f),
            StatisticsEntry.Weight(4, 48.5f),
            StatisticsEntry.Weight(5, 48.8f),
            StatisticsEntry.Weight(6, 49.0f),
            StatisticsEntry.Weight(7, 49.1f)
        )
    }
}