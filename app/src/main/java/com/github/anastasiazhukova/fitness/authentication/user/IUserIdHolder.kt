package com.github.anastasiazhukova.fitness.authentication.user

interface IUserIdHolder {
    fun getCurrentUserId(): String?
}