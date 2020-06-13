package com.github.anastasiazhukova.statistics.datasource.water

import com.github.anastasiazhukova.statistics.domain.StatisticsEntry

interface IWaterStatisticsRepository {
    suspend fun load(userId: String): List<StatisticsEntry>
}