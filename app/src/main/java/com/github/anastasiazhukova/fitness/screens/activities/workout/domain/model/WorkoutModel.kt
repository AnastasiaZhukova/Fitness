package com.github.anastasiazhukova.fitness.screens.activities.workout.domain.model

data class WorkoutModel(
    val items: List<ExerciseEntry>
)

data class ExerciseEntry(
    val id: String,
    val name: String,
    val description: String,
    val time: Int,
    val comments: String?
)