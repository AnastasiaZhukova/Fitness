package com.github.anastasiazhukova.fitness.screens.fragments.register.datasource

import com.github.anastasiazhukova.fitness.screens.fragments.register.domain.UserInfo

interface IUserInfoDataSource {
    suspend fun add(userInfo: UserInfo)
}