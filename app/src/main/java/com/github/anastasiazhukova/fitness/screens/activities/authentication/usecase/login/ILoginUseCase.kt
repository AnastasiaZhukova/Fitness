package com.github.anastasiazhukova.fitness.screens.activities.authentication.usecase.login

import com.github.anastasiazhukova.fitness.utils.Result

interface ILoginUseCase {
    suspend fun login(email: String, password: String): Result<Boolean>
}