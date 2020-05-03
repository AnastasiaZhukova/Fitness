package com.github.anastasiazhukova.fitness.screens.activities.workout.domain.params

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WorkoutParams(
    val entries: List<ExerciseParams>
) : Parcelable

@Parcelize
data class ExerciseParams(
    val id: String,
    val time: Int,
    val comments: String?
) : Parcelable