package com.github.anastasiazhukova.fitness.screens.fragments.login.presentation

sealed class LoginUiState {
    object Loading : LoginUiState()
    object Success : LoginUiState()
    object Error : LoginUiState()
}