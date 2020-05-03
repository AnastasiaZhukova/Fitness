package com.github.anastasiazhukova.fitness.screens.fragments.statistics.datasource.workout

import com.github.anastasiazhukova.fitness.screens.fragments.statistics.domain.StatisticsEntry

interface IWorkoutStatisticsRepository {
    suspend fun load(): List<StatisticsEntry>
}