package com.github.anastasiazhukova.fitness.admin.screens.fragment.login.presentation

sealed class LoginUiState {
    object Loading : LoginUiState()
    object Success : LoginUiState()
    object Error : LoginUiState()
}