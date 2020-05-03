package com.github.anastasiazhukova.fitness.datasource.user.workoutPlan

interface IWorkoutPlanDao {
    suspend fun add(userId: String, model: WorkoutPlanDataModel)
    suspend fun getAll(userId: String): List<WorkoutPlanDataModel>
    suspend fun get(userId: String, date: Long): WorkoutPlanDataModel?
    suspend fun update(userId: String, model: WorkoutPlanDataModel)
    suspend fun delete(userId: String, entryId: String)
}