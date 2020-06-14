package com.github.anastasiazhukova.fitness.screens.activities.main.usecase

import com.github.anastasiazhukova.fitness.domain.weight.WeightModel
import com.github.anastasiazhukova.fitness.screens.activities.main.datasource.userInfo.IUserInfoDataSource
import com.github.anastasiazhukova.fitness.screens.activities.main.datasource.weight.IWeightDataSource
import com.github.anastasiazhukova.fitness.utils.Result

class WeightUseCase(
    private val weightDataSource: IWeightDataSource,
    private val userInfoDataSource: IUserInfoDataSource
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
            userInfoDataSource.updateWeight(weightModel.weight)
            Result.Success(model)
        } catch (e: Exception) {
            Result.Error(e)
        }
}