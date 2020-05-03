package com.github.anastasiazhukova.fitness.screens.activities.workout.domain

sealed class WorkoutEntity {
    data class Exercise(
        val title: String?,
        val duration: Long?
    ) : WorkoutEntity()

    data class Break(
        val duration: Long?
    ) : WorkoutEntity()
}