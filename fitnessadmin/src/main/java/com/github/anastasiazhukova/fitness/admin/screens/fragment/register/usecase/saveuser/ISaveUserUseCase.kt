package com.github.anastasiazhukova.fitness.admin.screens.fragment.register.usecase.saveuser

import com.github.anastasiazhukova.fitness.admin.screens.fragment.register.domain.TrainerInfo
import com.github.anastasiazhukova.fitness.utils.Result

interface ISaveUserUseCase {
    suspend fun save(trainerInfo: TrainerInfo): Result<Boolean>
}