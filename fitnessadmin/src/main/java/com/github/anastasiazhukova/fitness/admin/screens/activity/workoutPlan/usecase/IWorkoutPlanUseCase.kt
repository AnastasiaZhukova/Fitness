package com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.usecase

import com.github.anastasiazhukova.fitness.domain.workoutPlan.ExerciseEntry
import com.github.anastasiazhukova.fitness.domain.workoutPlan.WorkoutPlanModel
import com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.domain.WorkoutPlanPageModel
import com.github.anastasiazhukova.fitness.utils.Result

interface IWorkoutPlanUseCase {

    suspend fun get(userId: String, date: Long): Result<WorkoutPlanPageModel>

    suspend fun add(
        userId: String,
        workoutPlanModel: WorkoutPlanModel,
        exerciseEntry: ExerciseEntry
    ): Result<WorkoutPlanPageModel>

    suspend fun update(
        userId: String,
        workoutPlanModel: WorkoutPlanModel,
        exerciseEntry: ExerciseEntry
    ): Result<WorkoutPlanPageModel>

    suspend fun update(
        userId: String,
        workoutPlanModel: WorkoutPlanModel
    ): Result<WorkoutPlanPageModel>

    suspend fun delete(
        userId: String,
        workoutPlanModel: WorkoutPlanModel,
        exerciseEntry: ExerciseEntry
    ): Result<WorkoutPlanPageModel>

    suspend fun delete(
        userId: String,
        workoutPlanModel: WorkoutPlanModel
    ): Result<WorkoutPlanPageModel>
}