package com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.usecase

import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.datasource.clients.IClientsDataSource
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.datasource.trainerInfo.ITrainerNicknameDataSource
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.domain.ClientModel
import com.github.anastasiazhukova.fitness.utils.Result

class ClientsUseCase(
    private val trainerNicknameDataSource: ITrainerNicknameDataSource,
    private val clientsDataSource: IClientsDataSource
) : IClientsUseCase {
    override suspend fun load(): Result<List<ClientModel>> =
        try {
            val trainerNickname = trainerNicknameDataSource.get()

            if (trainerNickname.isNotEmpty()) {
                Result.Success(clientsDataSource.get(trainerNickname))
            } else {
                Result.Success(emptyList())
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
}