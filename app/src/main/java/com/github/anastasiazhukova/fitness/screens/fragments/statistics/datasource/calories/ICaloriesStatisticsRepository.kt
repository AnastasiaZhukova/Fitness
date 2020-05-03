package com.github.anastasiazhukova.fitness.screens.fragments.statistics.datasource.calories

import com.github.anastasiazhukova.fitness.screens.fragments.statistics.domain.StatisticsEntry

interface ICaloriesStatisticsRepository {
    suspend fun load(): List<StatisticsEntry>
}