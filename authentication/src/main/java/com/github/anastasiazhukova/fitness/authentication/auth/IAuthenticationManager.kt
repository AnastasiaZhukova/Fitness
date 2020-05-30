package com.github.anastasiazhukova.fitness.authentication.auth

import com.github.anastasiazhukova.fitness.utils.Result

interface IAuthenticationManager {

    fun isLoggedIn(): Boolean

    suspend fun login(email: String, password: String): Result<String?>

    suspend fun register(email: String, password: String): Result<String?>

    suspend fun logout(): Result<Boolean>
}