package com.github.anastasiazhukova.fitness.datasource.user.weight

interface IWeightDao {
    suspend fun add(userId: String, model: WeightDataModel)
    suspend fun getAll(userId: String): List<WeightDataModel>
    suspend fun get(userId: String, date: Long): WeightDataModel?
    suspend fun update(userId: String, model: WeightDataModel)
    suspend fun delete(userId: String, entryId: String)
}