package com.github.anastasiazhukova.fitness.screens.activities.workout.presentation

import com.github.anastasiazhukova.fitness.screens.activities.workout.domain.model.WorkoutModel

sealed class WorkoutUiState {
    object Loading : WorkoutUiState()
    class Success(val model: WorkoutModel?) : WorkoutUiState()
    class Error(val e: Throwable) : WorkoutUiState()
}