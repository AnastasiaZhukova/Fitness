package com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.usecase.trainerInfo

import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.datasource.trainerInfo.ITrainerInfoDataSource
import com.github.anastasiazhukova.fitness.utils.Result

class TrainerInfoUseCase(
    private val trainerInfoDataSource: ITrainerInfoDataSource
) : ITrainerInfoUseCase {

    override suspend fun getTrainerNickname(): Result<String?> =
        try {
            Result.Success(trainerInfoDataSource.getTrainerNickname())
        } catch (e: Exception) {
            Result.Error(e)
        }
}