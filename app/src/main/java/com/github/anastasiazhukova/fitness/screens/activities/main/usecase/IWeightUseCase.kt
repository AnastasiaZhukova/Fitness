package com.github.anastasiazhukova.fitness.screens.activities.main.usecase

import com.github.anastasiazhukova.fitness.domain.weight.WeightModel
import com.github.anastasiazhukova.fitness.utils.Result

interface IWeightUseCase {
    suspend fun get(date: Long): Result<WeightModel?>
    suspend fun add(weightModel: WeightModel): Result<WeightModel>
}