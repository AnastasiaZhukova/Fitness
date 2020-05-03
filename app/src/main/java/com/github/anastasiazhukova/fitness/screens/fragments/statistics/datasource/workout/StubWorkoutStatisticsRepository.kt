package com.github.anastasiazhukova.fitness.screens.fragments.statistics.datasource.workout

import com.github.anastasiazhukova.fitness.screens.fragments.statistics.domain.StatisticsEntry

class StubWorkoutStatisticsRepository : IWorkoutStatisticsRepository {
    override suspend fun load(): List<StatisticsEntry> {
        return listOf(
            StatisticsEntry(1, 4.8f),
            StatisticsEntry(2, 3.9f),
            StatisticsEntry(3, 4.3f),
            StatisticsEntry(4, 4.5f),
            StatisticsEntry(5, 4.8f),
            StatisticsEntry(6, 4.0f),
            StatisticsEntry(7, 4.1f)
        )
    }
}