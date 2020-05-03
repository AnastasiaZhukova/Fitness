package com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.datasource.statistics.general

import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.domain.Statistics

class StubGeneralStatisticsRepository : IGeneralStatisticsRepository {
    override suspend fun load(): Statistics? {
        return Statistics.Value("4.5")
    }
}