package com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.datasource

import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.domain.ClientModel

interface IClientsDataSource {
    suspend fun get(): List<ClientModel>
}