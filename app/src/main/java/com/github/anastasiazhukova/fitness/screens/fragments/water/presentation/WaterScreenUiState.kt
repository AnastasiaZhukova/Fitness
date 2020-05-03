package com.github.anastasiazhukova.fitness.screens.fragments.water.presentation

import com.github.anastasiazhukova.fitness.screens.fragments.water.domain.WaterModel

sealed class WaterScreenUiState {
    object Loading : WaterScreenUiState()
    object OperationInProgress : WaterScreenUiState()
    class Success(val model: WaterModel?) : WaterScreenUiState()
    class Error(val e: Throwable) : WaterScreenUiState()
}