package com.github.anastasiazhukova.fitness.screens.activities.workout.datasource

import com.github.anastasiazhukova.fitness.screens.activities.workout.domain.model.WorkoutModel
import com.github.anastasiazhukova.fitness.screens.activities.workout.domain.params.WorkoutParams

interface IWorkoutDataSource {
    suspend fun load(workoutParams: WorkoutParams): WorkoutModel?
}