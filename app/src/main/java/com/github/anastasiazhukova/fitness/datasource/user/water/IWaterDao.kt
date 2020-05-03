package com.github.anastasiazhukova.fitness.datasource.user.water

interface IWaterDao {
    suspend fun add(userId: String, model: WaterDataModel)
    suspend fun getAll(userId: String): List<WaterDataModel>
    suspend fun get(userId: String, date: Long): WaterDataModel?
    suspend fun update(userId: String, model: WaterDataModel)
    suspend fun delete(userId: String, entryId: String)
}