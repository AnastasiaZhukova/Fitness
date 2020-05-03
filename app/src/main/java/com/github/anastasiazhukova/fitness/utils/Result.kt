package com.github.anastasiazhukova.fitness.utils

sealed class Result<out T> {
    class Success<out T>(val value: T) : Result<T>()
    class Error(val exception: Throwable) : Result<Nothing>()
}