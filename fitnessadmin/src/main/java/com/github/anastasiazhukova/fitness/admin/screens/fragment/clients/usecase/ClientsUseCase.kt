package com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.usecase

import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.datasource.IClientsDataSource
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.domain.ClientModel
import com.github.anastasiazhukova.fitness.utils.Result

class ClientsUseCase(
    private val clientsDataSource: IClientsDataSource
) : IClientsUseCase {
    override suspend fun load(): Result<List<ClientModel>> =
        try {
            Result.Success(clientsDataSource.get())
        } catch (e: Exception) {
            Result.Error(e)
        }
}