package com.github.anastasiazhukova.fitness.screens.fragments.zold.settings.usecase

import com.github.anastasiazhukova.fitness.screens.fragments.zold.food.domain.TodayStatisticsModel

interface ITodayStatisticsUseCase {
    suspend fun load(): TodayStatisticsModel
}