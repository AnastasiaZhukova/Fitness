package com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.datasource.statistics.today

import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.domain.Statistics

class StubTodayStatisticsRepository : ITodayStatisticsRepository {
    override suspend fun load(): Statistics? {
        return Statistics.Value("3.2")
    }
}