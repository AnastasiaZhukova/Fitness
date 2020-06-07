package com.github.anastasiazhukova.fitness.domain.workoutPlan

import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY

data class WorkoutPlanModel(
    val id: String = EMPTY,
    val date: Long = -1,
    val preview: String? = EMPTY,
    var duration: Int = 0,
    var calories: Int = 0,
    var level: Int = 1,
    val entries: MutableList<ExerciseEntry> = mutableListOf()
)

data class ExerciseEntry(
    val id: String = EMPTY,
    val relatedExerciseId: String = EMPTY,
    val name: String = EMPTY,
    val timeInMillis: Int = -1,
    val comments: String? = EMPTY
)