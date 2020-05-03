package com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.domain

import com.github.anastasiazhukova.fitness.screens.activities.workout.domain.params.ExerciseParams
import com.github.anastasiazhukova.fitness.screens.activities.workout.domain.params.WorkoutParams
import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY

data class WorkoutPlanModel(
    val id: String = EMPTY,
    val date: Long = -1,
    val preview: String? = null,
    val duration: Int? = null,
    val calories: Int? = null,
    val level: Int? = null,
    val entries: List<ExerciseEntry> = emptyList()
)

data class ExerciseEntry(
    val id: String = EMPTY,
    val time: Int = -1,
    val comments: String = EMPTY
)

fun WorkoutPlanModel.toWorkoutParams(): WorkoutParams {
    val exerciseParamsList = entries.map { entry ->
        ExerciseParams(
            id = entry.id,
            time = entry.time,
            comments = entry.comments
        )
    }

    return WorkoutParams(exerciseParamsList)
}