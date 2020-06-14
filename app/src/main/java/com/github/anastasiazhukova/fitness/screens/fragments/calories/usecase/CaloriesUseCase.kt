package com.github.anastasiazhukova.fitness.screens.fragments.calories.usecase

import com.github.anastasiazhukova.fitness.screens.fragments.calories.datasource.ICaloriesDataSource
import com.github.anastasiazhukova.fitness.domain.calories.CaloriesEntry
import com.github.anastasiazhukova.fitness.domain.calories.CaloriesModel
import com.github.anastasiazhukova.fitness.utils.Result

class CaloriesUseCase(
    private val caloriesDataSource: ICaloriesDataSource
) : ICaloriesUseCase {

    override suspend fun get(date: Long): Result<CaloriesModel?> {
        return try {
            Result.Success(caloriesDataSource.get(date))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun add(
        caloriesModel: CaloriesModel,
        caloriesEntry: CaloriesEntry
    ): Result<CaloriesModel> =
        try {
            val model = caloriesDataSource.add(caloriesModel, caloriesEntry)
            Result.Success(model)
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun update(
        caloriesModel: CaloriesModel,
        caloriesEntry: CaloriesEntry
    ): Result<CaloriesModel> =
        try {
            val model = caloriesDataSource.update(caloriesModel, caloriesEntry)
            Result.Success(model)
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun delete(
        caloriesModel: CaloriesModel,
        caloriesEntry: CaloriesEntry
    ): Result<CaloriesModel> =
        try {
            val model = caloriesDataSource.delete(caloriesModel, caloriesEntry)
            Result.Success(model)
        } catch (e: Exception) {
            Result.Error(e)
        }
}