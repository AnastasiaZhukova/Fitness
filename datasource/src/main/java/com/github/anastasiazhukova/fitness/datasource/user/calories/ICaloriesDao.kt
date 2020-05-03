package com.github.anastasiazhukova.fitness.datasource.user.calories

interface ICaloriesDao {
    suspend fun add(userId: String, model: CaloriesDataModel)
    suspend fun getAll(userId: String): List<CaloriesDataModel>
    suspend fun get(userId: String, date: Long): CaloriesDataModel?
    suspend fun update(userId: String, model: CaloriesDataModel)
    suspend fun delete(userId: String, entryId: String)
}