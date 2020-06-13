package com.github.anastasiazhukova.statistics.usecase

import com.github.anastasiazhukova.fitness.utils.Result
import com.github.anastasiazhukova.statistics.datasource.calories.ICaloriesStatisticsRepository
import com.github.anastasiazhukova.statistics.datasource.water.IWaterStatisticsRepository
import com.github.anastasiazhukova.statistics.datasource.weight.IWeightStatisticsRepository
import com.github.anastasiazhukova.statistics.domain.StatisticsModel
import com.github.anastasiazhukova.statistics.domain.StatisticsPageModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class StatisticsUseCase(
    private val caloriesStatisticsRepository: ICaloriesStatisticsRepository,
    private val weightStatisticsRepository: IWeightStatisticsRepository,
    private val waterStatisticsRepository: IWaterStatisticsRepository
) : IStatisticsUseCase {
    override suspend fun load(userId: String): Result<StatisticsPageModel> {
        return coroutineScope {
            val caloriesAsync = async { caloriesStatisticsRepository.load(userId) }
            val weightAsync = async { weightStatisticsRepository.load(userId) }
            val waterAsync = async { waterStatisticsRepository.load(userId) }

            val caloriesStatistics = caloriesAsync.await()
            val weightStatistics = weightAsync.await()
            val waterStatistics = waterAsync.await()

            Result.Success(
                StatisticsPageModel(
                    listOf(
                        StatisticsModel("Weight", weightStatistics),
                        StatisticsModel("Calories", caloriesStatistics),
                        StatisticsModel("Water", waterStatistics)
                    )
                )
            )
        }
    }
}