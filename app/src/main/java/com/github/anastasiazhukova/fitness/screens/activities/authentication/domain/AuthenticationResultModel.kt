package com.github.anastasiazhukova.fitness.screens.activities.authentication.domain

sealed class AuthenticationResultModel {

    object Success : AuthenticationResultModel()

    data class Error(
        val error: String? = null,
        val suggestion: String? = null
    ) : AuthenticationResultModel()
}