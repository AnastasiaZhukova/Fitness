package com.github.anastasiazhukova.fitness.admin.screens.fragment.register.presentation

sealed class RegisterUiState {
    object Loading : RegisterUiState()
    object Success : RegisterUiState()

    sealed class Error : RegisterUiState() {
        object UserExists : Error()
        object Other : Error()
    }
}