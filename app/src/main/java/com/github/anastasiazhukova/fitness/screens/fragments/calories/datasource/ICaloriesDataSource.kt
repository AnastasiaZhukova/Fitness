package com.github.anastasiazhukova.fitness.screens.fragments.calories.datasource

import com.github.anastasiazhukova.fitness.domain.calories.CaloriesEntry
import com.github.anastasiazhukova.fitness.domain.calories.CaloriesModel

interface ICaloriesDataSource {
    suspend fun get(date: Long): CaloriesModel?
    suspend fun add(caloriesModel: CaloriesModel, caloriesEntry: CaloriesEntry): CaloriesModel
    suspend fun update(caloriesModel: CaloriesModel, caloriesEntry: CaloriesEntry): CaloriesModel
    suspend fun delete(caloriesModel: CaloriesModel, caloriesEntry: CaloriesEntry): CaloriesModel
}