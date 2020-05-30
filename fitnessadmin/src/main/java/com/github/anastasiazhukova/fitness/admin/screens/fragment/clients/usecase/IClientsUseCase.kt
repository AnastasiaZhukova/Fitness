package com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.usecase

import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.domain.ClientModel
import com.github.anastasiazhukova.fitness.utils.Result

interface IClientsUseCase {
    suspend fun load(): Result<List<ClientModel>>
}