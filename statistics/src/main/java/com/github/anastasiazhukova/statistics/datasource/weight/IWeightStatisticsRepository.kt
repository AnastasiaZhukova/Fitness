package com.github.anastasiazhukova.statistics.datasource.weight

import com.github.anastasiazhukova.statistics.domain.StatisticsEntry

interface IWeightStatisticsRepository {
    suspend fun load(userId: String): List<StatisticsEntry>
}