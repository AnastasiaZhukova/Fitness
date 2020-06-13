package com.github.anastasiazhukova.fitness.screens.fragments.calories.presentation

import com.github.anastasiazhukova.fitness.domain.calories.CaloriesModel

sealed class CaloriesScreenUiState {
    object Loading : CaloriesScreenUiState()
    object OperationInProgress : CaloriesScreenUiState()
    class Success(val model: CaloriesModel?) : CaloriesScreenUiState()
    class Error(val e: Throwable) : CaloriesScreenUiState()
}