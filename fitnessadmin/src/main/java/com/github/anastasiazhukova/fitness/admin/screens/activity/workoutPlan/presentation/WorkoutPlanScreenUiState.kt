package com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.presentation

import com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.domain.WorkoutPlanPageModel

sealed class WorkoutPlanScreenUiState {
    object Loading : WorkoutPlanScreenUiState()
    object OperationInProgress : WorkoutPlanScreenUiState()
    class Success(val model: WorkoutPlanPageModel?) : WorkoutPlanScreenUiState()
    class Error(val e: Throwable) : WorkoutPlanScreenUiState()
}