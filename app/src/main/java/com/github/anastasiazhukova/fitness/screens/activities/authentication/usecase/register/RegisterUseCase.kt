package com.github.anastasiazhukova.fitness.screens.activities.authentication.usecase.register

import com.github.anastasiazhukova.fitness.authentication.auth.IAuthenticationManager
import com.github.anastasiazhukova.fitness.utils.Result

class RegisterUseCase(
    private val authenticationManager: IAuthenticationManager
) : IRegisterUseCase {

    override suspend fun register(email: String, password: String): Result<Boolean> {
        return authenticationManager.register(email, password)
    }
}