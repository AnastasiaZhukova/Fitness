package com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.usecase.trainerInfo

import com.github.anastasiazhukova.fitness.utils.Result

interface ITrainerInfoUseCase {
    suspend fun getTrainerNickname(): Result<String?>
}