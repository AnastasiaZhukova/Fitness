package com.github.anastasiazhukova.fitness.screens.fragments.login.usecase

import com.github.anastasiazhukova.fitness.authentication.auth.IAuthenticationManager
import com.github.anastasiazhukova.fitness.utils.Result

class LoginUseCase(
    private val authenticationManager: IAuthenticationManager
) : ILoginUseCase {

    override suspend fun login(email: String, password: String): Result<String?> {
        return authenticationManager.login(email, password)
    }
}