package com.github.anastasiazhukova.fitness.screens.activities.workout.usecase

import com.github.anastasiazhukova.fitness.screens.activities.workout.datasource.IWorkoutDataSource
import com.github.anastasiazhukova.fitness.screens.activities.workout.domain.model.WorkoutModel
import com.github.anastasiazhukova.fitness.screens.activities.workout.domain.params.WorkoutParams
import com.github.anastasiazhukova.fitness.utils.Result

class WorkoutUseCase(
    private val workoutDataSource: IWorkoutDataSource
) : IWorkoutUseCase {

    override suspend fun load(workoutParams: WorkoutParams): Result<WorkoutModel?> =
        try {
            Result.Success(workoutDataSource.load(workoutParams))
        } catch (e: Exception) {
            Result.Error(e)
        }
}