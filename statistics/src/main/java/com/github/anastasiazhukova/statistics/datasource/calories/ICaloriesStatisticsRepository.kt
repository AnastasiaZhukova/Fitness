package com.github.anastasiazhukova.statistics.datasource.calories

import com.github.anastasiazhukova.statistics.domain.StatisticsEntry

interface ICaloriesStatisticsRepository {
    suspend fun load(userId: String): List<StatisticsEntry>
}