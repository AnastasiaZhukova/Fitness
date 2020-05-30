package com.github.anastasiazhukova.fitness.screens.fragments.register.usecase.saveuser

import com.github.anastasiazhukova.fitness.screens.fragments.register.datasource.IUserInfoDataSource
import com.github.anastasiazhukova.fitness.screens.fragments.register.domain.UserInfo
import com.github.anastasiazhukova.fitness.utils.Result

class SaveUserUseCase(
    private val dataSource: IUserInfoDataSource
) : ISaveUserUseCase {

    override suspend fun save(userInfo: UserInfo): Result<Boolean> =
        try {
            dataSource.add(userInfo)
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }
}