package com.github.anastasiazhukova.fitness.screens.fragments.register.usecase.register

import com.github.anastasiazhukova.fitness.utils.Result

interface IRegisterUseCase {
    suspend fun register(email: String, password: String): Result<String?>
}