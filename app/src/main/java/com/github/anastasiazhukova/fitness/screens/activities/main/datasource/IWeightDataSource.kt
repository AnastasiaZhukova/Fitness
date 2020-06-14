package com.github.anastasiazhukova.fitness.screens.activities.main.datasource

import com.github.anastasiazhukova.fitness.domain.water.WaterEntry
import com.github.anastasiazhukova.fitness.domain.water.WaterModel
import com.github.anastasiazhukova.fitness.domain.weight.WeightModel

interface IWeightDataSource {
    suspend fun get(date: Long): WeightModel?
    suspend fun add(weightModel: WeightModel): WeightModel
}