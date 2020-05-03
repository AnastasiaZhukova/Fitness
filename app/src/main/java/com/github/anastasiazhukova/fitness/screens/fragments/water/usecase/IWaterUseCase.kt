package com.github.anastasiazhukova.fitness.screens.fragments.water.usecase

import com.github.anastasiazhukova.fitness.screens.fragments.water.domain.WaterEntry
import com.github.anastasiazhukova.fitness.screens.fragments.water.domain.WaterModel
import com.github.anastasiazhukova.fitness.utils.Result

interface IWaterUseCase {
    suspend fun get(date: Long): Result<WaterModel?>
    suspend fun add(waterModel: WaterModel, waterEntry: WaterEntry): Result<WaterModel>
    suspend fun delete(waterModel: WaterModel, waterEntry: WaterEntry): Result<WaterModel>
}