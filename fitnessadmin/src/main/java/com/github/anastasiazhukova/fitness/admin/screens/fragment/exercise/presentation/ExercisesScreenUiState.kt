package com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.presentation

import com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.domain.ExerciseModel

sealed class ExercisesScreenUiState {
    object Loading : ExercisesScreenUiState()
    object OperationInProgress : ExercisesScreenUiState()
    class Success(val model: List<ExerciseModel>) : ExercisesScreenUiState()
    class Error(val e: Throwable) : ExercisesScreenUiState()
}