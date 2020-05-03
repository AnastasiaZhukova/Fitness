package com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.datasource.statistics.today

import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.domain.Statistics

interface ITodayStatisticsRepository {
    suspend fun load(): Statistics?
}