package com.github.anastasiazhukova.fitness.admin.screens.fragment.login.usecase

import com.github.anastasiazhukova.fitness.utils.Result

interface ILoginUseCase {
    suspend fun login(email: String, password: String): Result<String?>
}