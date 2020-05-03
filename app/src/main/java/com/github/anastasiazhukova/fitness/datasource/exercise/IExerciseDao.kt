package com.github.anastasiazhukova.fitness.datasource.exercise

interface IExerciseDao {
    suspend fun add(model: ExerciseDataModel)
    suspend fun getAll(): List<ExerciseDataModel>
    suspend fun get(id: String): ExerciseDataModel?
    suspend fun update(model: ExerciseDataModel)
    suspend fun delete(id: String)
}