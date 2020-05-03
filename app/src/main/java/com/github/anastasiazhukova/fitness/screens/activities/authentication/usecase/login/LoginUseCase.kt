package com.github.anastasiazhukova.fitness.screens.activities.authentication.usecase.login

import com.github.anastasiazhukova.fitness.authentication.auth.IAuthenticationManager
import com.github.anastasiazhukova.fitness.utils.Result

class LoginUseCase(
    private val authenticationManager: IAuthenticationManager
) : ILoginUseCase {

    override suspend fun login(email: String, password: String): Result<Boolean> {
        return authenticationManager.login(email, password)
    }
}