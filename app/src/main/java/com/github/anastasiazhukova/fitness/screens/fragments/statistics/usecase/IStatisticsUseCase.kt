package com.github.anastasiazhukova.fitness.screens.fragments.statistics.usecase

import com.github.anastasiazhukova.fitness.screens.fragments.statistics.domain.StatisticsPageModel

interface IStatisticsUseCase {
    suspend fun load(): StatisticsPageModel
}