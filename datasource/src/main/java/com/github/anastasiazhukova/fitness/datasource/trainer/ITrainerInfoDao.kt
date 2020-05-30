package com.github.anastasiazhukova.fitness.datasource.trainer

interface ITrainerInfoDao {
    suspend fun add(model: TrainerInfoDataModel)
    suspend fun get(trainerId: String): TrainerInfoDataModel?
    suspend fun update(model: TrainerInfoDataModel)
    suspend fun delete(trainerId: String)
}