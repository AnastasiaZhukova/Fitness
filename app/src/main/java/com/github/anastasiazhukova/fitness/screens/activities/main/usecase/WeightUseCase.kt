package com.github.anastasiazhukova.fitness.screens.activities.main.usecase

import com.github.anastasiazhukova.fitness.domain.weight.WeightModel
import com.github.anastasiazhukova.fitness.screens.activities.main.datasource.IWeightDataSource
import com.github.anastasiazhukova.fitness.utils.Result

class WeightUseCase(
    private val weightDataSource: IWeightDataSource
) : IWeightUseCase {

    override suspend fun get(date: Long): Result<WeightModel?> {
        return try {
            Result.Success(weightDataSource.get(date))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun add(weightModel: WeightModel): Result<WeightModel> =
        try {
            val model = weightDataSource.add(weightModel)
            Result.Success(model)
        } catch (e: Exception) {
            Result.Error(e)
        }
}