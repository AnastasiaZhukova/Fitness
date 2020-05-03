package com.github.anastasiazhukova.fitness.screens.activities.workout.usecase

import com.github.anastasiazhukova.fitness.screens.activities.workout.domain.model.WorkoutModel
import com.github.anastasiazhukova.fitness.screens.activities.workout.domain.params.WorkoutParams
import com.github.anastasiazhukova.fitness.utils.Result

interface IWorkoutUseCase {
    suspend fun load(workoutParams: WorkoutParams): Result<WorkoutModel?>
}