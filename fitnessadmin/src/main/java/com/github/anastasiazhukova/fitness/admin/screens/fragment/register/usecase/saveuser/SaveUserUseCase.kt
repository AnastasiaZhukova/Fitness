package com.github.anastasiazhukova.fitness.admin.screens.fragment.register.usecase.saveuser

import com.github.anastasiazhukova.fitness.admin.screens.fragment.register.datasource.ITrainerInfoDataSource
import com.github.anastasiazhukova.fitness.admin.screens.fragment.register.domain.TrainerInfo
import com.github.anastasiazhukova.fitness.admin.screens.fragment.register.usecase.saveuser.ISaveUserUseCase
import com.github.anastasiazhukova.fitness.utils.Result

class SaveUserUseCase(
    private val dataSource: ITrainerInfoDataSource
) : ISaveUserUseCase {

    override suspend fun save(trainerInfo: TrainerInfo): Result<Boolean> =
        try {
            dataSource.add(trainerInfo)
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }
}