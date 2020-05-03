package com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.usecase

import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.domain.WorkoutPlanModel
import com.github.anastasiazhukova.fitness.utils.Result

interface IWorkoutPlanUseCase {
    suspend fun get(date: Long): Result<WorkoutPlanModel?>
}