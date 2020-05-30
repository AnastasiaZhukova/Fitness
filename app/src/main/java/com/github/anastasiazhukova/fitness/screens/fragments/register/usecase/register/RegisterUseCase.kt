package com.github.anastasiazhukova.fitness.screens.fragments.register.usecase.register

import com.github.anastasiazhukova.fitness.authentication.auth.IAuthenticationManager
import com.github.anastasiazhukova.fitness.screens.fragments.register.usecase.register.IRegisterUseCase
import com.github.anastasiazhukova.fitness.utils.Result

class RegisterUseCase(
    private val authenticationManager: IAuthenticationManager
) : IRegisterUseCase {

    override suspend fun register(email: String, password: String): Result<String?> {
        return authenticationManager.register(email, password)
    }
}