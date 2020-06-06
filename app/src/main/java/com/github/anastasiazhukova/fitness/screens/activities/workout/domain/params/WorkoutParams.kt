package com.github.anastasiazhukova.fitness.screens.activities.workout.domain.params

import android.os.Parcelable
import com.github.anastasiazhukova.fitness.domain.workoutPlan.WorkoutPlanModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WorkoutParams(
    val entries: List<ExerciseParams>
) : Parcelable

@Parcelize
data class ExerciseParams(
    val id: String,
    val relatedExerciseId: String,
    val time: Int,
    val comments: String?
) : Parcelable

fun WorkoutPlanModel.toWorkoutParams(): WorkoutParams {
    val exerciseParamsList = entries.map { entry ->
        ExerciseParams(
            id = entry.id,
            relatedExerciseId = entry.relatedExerciseId,
            time = entry.timeInMillis,
            comments = entry.comments
        )
    }

    return WorkoutParams(
        exerciseParamsList
    )
}