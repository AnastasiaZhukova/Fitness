package com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.datasource.clients

import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.domain.ClientModel

interface IClientsDataSource {
    suspend fun get(trainerNickname: String): List<ClientModel>
}