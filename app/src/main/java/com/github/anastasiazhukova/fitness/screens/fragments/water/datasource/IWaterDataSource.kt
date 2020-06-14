package com.github.anastasiazhukova.fitness.screens.fragments.water.datasource

import com.github.anastasiazhukova.fitness.domain.water.WaterEntry
import com.github.anastasiazhukova.fitness.domain.water.WaterModel

interface IWaterDataSource {
    suspend fun get(date: Long): WaterModel?
    suspend fun add(waterModel: WaterModel, waterEntry: WaterEntry): WaterModel
    suspend fun update(waterModel: WaterModel, waterEntry: WaterEntry): WaterModel
    suspend fun delete(waterModel: WaterModel, waterEntry: WaterEntry): WaterModel
}