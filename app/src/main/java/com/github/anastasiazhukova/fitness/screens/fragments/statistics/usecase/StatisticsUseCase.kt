package com.github.anastasiazhukova.fitness.screens.fragments.statistics.usecase

import com.github.anastasiazhukova.fitness.screens.fragments.statistics.datasource.calories.ICaloriesStatisticsRepository
import com.github.anastasiazhukova.fitness.screens.fragments.statistics.datasource.weight.IWeightStatisticsRepository
import com.github.anastasiazhukova.fitness.screens.fragments.statistics.datasource.workout.IWorkoutStatisticsRepository
import com.github.anastasiazhukova.fitness.screens.fragments.statistics.domain.StatisticsModel
import com.github.anastasiazhukova.fitness.screens.fragments.statistics.domain.StatisticsPageModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class StatisticsUseCase(
    private val caloriesStatisticsRepository: ICaloriesStatisticsRepository,
    private val weightStatisticsRepository: IWeightStatisticsRepository,
    private val workoutStatisticsRepository: IWorkoutStatisticsRepository
) : IStatisticsUseCase {
    override suspend fun load(): StatisticsPageModel {
        return coroutineScope {
            val caloriesAsync = async { caloriesStatisticsRepository.load() }
            val weightAsync = async { weightStatisticsRepository.load() }
            val workoutAsync = async { workoutStatisticsRepository.load() }

            val caloriesStatistics = caloriesAsync.await()
            val weightStatistics = weightAsync.await()
            val workoutStatistics = workoutAsync.await()

            StatisticsPageModel(
                listOf(
                    StatisticsModel("Weight", weightStatistics),
                    StatisticsModel("Calories", caloriesStatistics),
                    StatisticsModel("Workout", workoutStatistics)
                )
            )
        }
    }
}