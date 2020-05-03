package com.github.anastasiazhukova.fitness.screens.fragments.water.usecase

import com.github.anastasiazhukova.fitness.screens.fragments.water.datasource.IWaterDataSource
import com.github.anastasiazhukova.fitness.screens.fragments.water.domain.WaterEntry
import com.github.anastasiazhukova.fitness.screens.fragments.water.domain.WaterModel
import com.github.anastasiazhukova.fitness.utils.Result

class WaterUseCase(
    private val waterDataSource: IWaterDataSource
) : IWaterUseCase {

    override suspend fun get(date: Long): Result<WaterModel?> {
        return try {
            Result.Success(waterDataSource.get(date))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun add(
        waterModel: WaterModel,
        waterEntry: WaterEntry
    ): Result<WaterModel> =
        try {
            val model = waterDataSource.add(waterModel, waterEntry)
            Result.Success(model)
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun delete(
        waterModel: WaterModel,
        waterEntry: WaterEntry
    ): Result<WaterModel> =
        try {
            val model = waterDataSource.delete(waterModel, waterEntry)
            Result.Success(model)
        } catch (e: Exception) {
            Result.Error(e)
        }
}