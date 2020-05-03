package com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.datasource.statistics.general

import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.domain.Statistics

interface IGeneralStatisticsRepository {
    suspend fun load(): Statistics?
}