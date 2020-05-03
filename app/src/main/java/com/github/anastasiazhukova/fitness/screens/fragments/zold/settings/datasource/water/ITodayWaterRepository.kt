package com.github.anastasiazhukova.fitness.screens.fragments.zold.settings.datasource.water

import com.github.anastasiazhukova.fitness.screens.fragments.zold.food.domain.TodayWaterModel

interface ITodayWaterRepository {
    suspend fun load(): TodayWaterModel?
}