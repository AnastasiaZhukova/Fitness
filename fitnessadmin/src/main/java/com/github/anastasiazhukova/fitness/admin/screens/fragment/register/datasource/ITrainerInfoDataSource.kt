package com.github.anastasiazhukova.fitness.admin.screens.fragment.register.datasource

import com.github.anastasiazhukova.fitness.admin.screens.fragment.register.domain.TrainerInfo

interface ITrainerInfoDataSource {
    suspend fun add(userInfo: TrainerInfo)
}