package com.github.anastasiazhukova.fitness.screens.fragments.register.presentation

sealed class RegisterUiState {
    object Loading : RegisterUiState()
    object Success : RegisterUiState()

    sealed class Error : RegisterUiState() {
        object UserExists : Error()
        object Other : Error()
    }
}