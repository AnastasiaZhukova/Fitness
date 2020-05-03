package com.github.anastasiazhukova.fitness.screens.fragments.statistics.datasource.weight

import com.github.anastasiazhukova.fitness.screens.fragments.statistics.domain.StatisticsEntry

interface IWeightStatisticsRepository {
    suspend fun load(): List<StatisticsEntry>
}