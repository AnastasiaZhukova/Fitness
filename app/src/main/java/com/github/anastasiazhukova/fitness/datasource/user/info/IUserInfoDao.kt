package com.github.anastasiazhukova.fitness.datasource.user.info

interface IUserInfoDao {
    suspend fun add(model: UserInfoDataModel)
    suspend fun get(userId: String): UserInfoDataModel?
    suspend fun update(model: UserInfoDataModel)
    suspend fun delete(userId: String)
}