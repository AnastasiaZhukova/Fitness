package com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.datasource.trainerInfo

interface ITrainerInfoDataSource {
    suspend fun getTrainerNickname(): String?
}