package com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.domain

sealed class Workout {

    data class Break(val nextWorkoutDay: String?) : Workout()

    data class Ready(
        val totalTime: String?,
        val totalCalories: String?
    ) : Workout()
}