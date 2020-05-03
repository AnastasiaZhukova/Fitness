package com.github.anastasiazhukova.fitness.screens.fragments.calories.usecase

import com.github.anastasiazhukova.fitness.screens.fragments.calories.domain.CaloriesEntry
import com.github.anastasiazhukova.fitness.screens.fragments.calories.domain.CaloriesModel
import com.github.anastasiazhukova.fitness.utils.Result

interface ICaloriesUseCase {
    suspend fun get(date: Long): Result<CaloriesModel?>
    suspend fun add(caloriesModel: CaloriesModel, caloriesEntry: CaloriesEntry): Result<CaloriesModel>
    suspend fun delete(caloriesModel: CaloriesModel, caloriesEntry: CaloriesEntry): Result<CaloriesModel>
}