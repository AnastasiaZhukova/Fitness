package com.github.anastasiazhukova.fitness.screens.activities.main.datasource.userInfo

interface IUserInfoDataSource {
    suspend fun updateWeight(weight: Float)
}