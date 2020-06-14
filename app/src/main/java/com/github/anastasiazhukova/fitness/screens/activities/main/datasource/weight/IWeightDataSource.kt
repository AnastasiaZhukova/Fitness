package com.github.anastasiazhukova.fitness.screens.activities.main.datasource.weight

import com.github.anastasiazhukova.fitness.domain.weight.WeightModel

interface IWeightDataSource {
    suspend fun get(date: Long): WeightModel?
    suspend fun add(weightModel: WeightModel): WeightModel
}