package com.github.anastasiazhukova.fitness.screens.fragments.zold.settings.usecase

import com.github.anastasiazhukova.fitness.screens.fragments.zold.food.datasource.calories.ITodayCaloriesRepository
import com.github.anastasiazhukova.fitness.screens.fragments.zold.food.datasource.water.ITodayWaterRepository
import com.github.anastasiazhukova.fitness.screens.fragments.zold.food.domain.TodayStatisticsModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class TodayStatisticsUseCase(
    private val todayCaloriesRepository: ITodayCaloriesRepository,
    private val todayWaterRepository: ITodayWaterRepository
) : ITodayStatisticsUseCase {
    override suspend fun load(): TodayStatisticsModel {
        return coroutineScope {
            val caloriesAsync = async { todayCaloriesRepository.load() }
            val waterAsync = async { todayWaterRepository.load() }

            val calories = caloriesAsync.await()
            val water = waterAsync.await()

            TodayStatisticsModel(
                caloriesModel = calories,
                waterModel = water
            )
        }
    }
}