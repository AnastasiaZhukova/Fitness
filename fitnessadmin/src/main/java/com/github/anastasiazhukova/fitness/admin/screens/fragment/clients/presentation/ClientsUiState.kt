package com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.presentation

import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.domain.ClientModel

sealed class ClientsUiState {
    object Loading : ClientsUiState()
    class Success(val model: List<ClientModel>) : ClientsUiState()
    class Error(val e: Throwable) : ClientsUiState()
}