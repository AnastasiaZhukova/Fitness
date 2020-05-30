package com.github.anastasiazhukova.fitness.datasource.user.info

interface IUserInfoDao {
    suspend fun add(model: UserInfoDataModel)
    suspend fun get(userId: String): UserInfoDataModel?
    suspend fun getByTrainerId(trainerId: String): List<UserInfoDataModel>
    suspend fun update(model: UserInfoDataModel)
    suspend fun delete(userId: String)
}