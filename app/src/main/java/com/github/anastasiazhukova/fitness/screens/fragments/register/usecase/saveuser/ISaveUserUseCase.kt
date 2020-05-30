package com.github.anastasiazhukova.fitness.screens.fragments.register.usecase.saveuser

import com.github.anastasiazhukova.fitness.screens.fragments.register.domain.UserInfo
import com.github.anastasiazhukova.fitness.utils.Result

interface ISaveUserUseCase {
    suspend fun save(userInfo: UserInfo): Result<Boolean>
}