package com.github.anastasiazhukova.fitness.admin.screens.fragment.register.usecase.register

import com.github.anastasiazhukova.fitness.utils.Result

interface IRegisterUseCase {
    suspend fun register(email: String, password: String): Result<String?>
}