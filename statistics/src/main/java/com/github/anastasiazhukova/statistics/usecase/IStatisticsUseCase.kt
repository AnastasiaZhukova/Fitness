package com.github.anastasiazhukova.statistics.usecase

import com.github.anastasiazhukova.fitness.utils.Result
import com.github.anastasiazhukova.statistics.domain.StatisticsPageModel

interface IStatisticsUseCase {
    suspend fun load(userId: String): Result<StatisticsPageModel>
}