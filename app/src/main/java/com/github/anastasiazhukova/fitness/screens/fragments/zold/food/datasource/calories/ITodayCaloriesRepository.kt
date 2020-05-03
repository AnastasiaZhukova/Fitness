package com.github.anastasiazhukova.fitness.screens.fragments.zold.food.datasource.calories

import com.github.anastasiazhukova.fitness.screens.fragments.zold.food.domain.TodayCaloriesModel

interface ITodayCaloriesRepository {
    suspend fun load(): TodayCaloriesModel?
}