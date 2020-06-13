package com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.datasource.trainerInfo

interface ITrainerNicknameDataSource {
    suspend fun get(): String
}