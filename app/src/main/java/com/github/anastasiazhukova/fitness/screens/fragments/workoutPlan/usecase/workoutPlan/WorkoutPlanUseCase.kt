package com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.usecase.workoutPlan

import com.github.anastasiazhukova.fitness.domain.workoutPlan.WorkoutPlanModel
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.datasource.workoutPlan.IWorkoutPlanDataSource
import com.github.anastasiazhukova.fitness.utils.Result

class WorkoutPlanUseCase(
    private val workoutPlanDataSource: IWorkoutPlanDataSource
) : IWorkoutPlanUseCase {

    override suspend fun get(date: Long): Result<WorkoutPlanModel?> =
        try {
            Result.Success(workoutPlanDataSource.get(date))
        } catch (e: Exception) {
            Result.Error(e)
        }
}