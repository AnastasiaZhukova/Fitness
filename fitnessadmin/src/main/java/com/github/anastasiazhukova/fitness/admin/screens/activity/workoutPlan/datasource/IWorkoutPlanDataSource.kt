package com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.datasource

import com.github.anastasiazhukova.fitness.domain.workoutPlan.ExerciseEntry
import com.github.anastasiazhukova.fitness.domain.workoutPlan.WorkoutPlanModel

interface IWorkoutPlanDataSource {

    suspend fun get(userId: String, date: Long): WorkoutPlanModel?

    suspend fun add(
        userId: String,
        workoutPlanModel: WorkoutPlanModel,
        exerciseEntry: ExerciseEntry
    ): WorkoutPlanModel

    suspend fun update(
        userId: String,
        workoutPlanModel: WorkoutPlanModel,
        exerciseEntry: ExerciseEntry
    ): WorkoutPlanModel

    suspend fun update(
        userId: String,
        workoutPlanModel: WorkoutPlanModel
    ): WorkoutPlanModel

    suspend fun delete(
        userId: String,
        workoutPlanModel: WorkoutPlanModel,
        exerciseEntry: ExerciseEntry
    ): WorkoutPlanModel

    suspend fun delete(
        userId: String,
        workoutPlanModel: WorkoutPlanModel
    ): WorkoutPlanModel?
}