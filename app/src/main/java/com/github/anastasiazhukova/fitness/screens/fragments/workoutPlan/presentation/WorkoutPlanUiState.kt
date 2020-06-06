package com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.presentation

import com.github.anastasiazhukova.fitness.domain.workoutPlan.WorkoutPlanModel

sealed class WorkoutPlanUiState {
    object Loading : WorkoutPlanUiState()
    class Success(val model: WorkoutPlanModel?) : WorkoutPlanUiState()
    class Error(val e: Throwable) : WorkoutPlanUiState()
}