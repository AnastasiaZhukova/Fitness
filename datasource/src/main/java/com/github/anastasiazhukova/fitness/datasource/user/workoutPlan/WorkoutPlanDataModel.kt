package com.github.anastasiazhukova.fitness.datasource.user.workoutPlan

import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.WorkoutPlanDaoConstants.CALORIES
import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.WorkoutPlanDaoConstants.COMMENTS
import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.WorkoutPlanDaoConstants.DATE
import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.WorkoutPlanDaoConstants.DURATION
import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.WorkoutPlanDaoConstants.EXERCISE_ENTRIES
import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.WorkoutPlanDaoConstants.EXERCISE_ENTRY_ID
import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.WorkoutPlanDaoConstants.ID
import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.WorkoutPlanDaoConstants.LEVEL
import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.WorkoutPlanDaoConstants.PREVIEW
import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.WorkoutPlanDaoConstants.RELATED_EXERCISE_ENTRY_ID
import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.WorkoutPlanDaoConstants.TIME
import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY
import com.google.gson.annotations.SerializedName

data class WorkoutPlanDataModel(
    @SerializedName(ID)
    val id: String = EMPTY,

    @SerializedName(DATE)
    val date: Long? = null,

    @SerializedName(PREVIEW)
    val preview: String? = null,

    @SerializedName(DURATION)
    val duration: Int? = null,

    @SerializedName(CALORIES)
    val calories: Int? = null,

    @SerializedName(LEVEL)
    val level: Int? = null,

    @SerializedName(EXERCISE_ENTRIES)
    val exerciseEntries: List<ExerciseEntryDataModel> = emptyList()
)

data class ExerciseEntryDataModel(
    @SerializedName(EXERCISE_ENTRY_ID)
    val id: String = EMPTY,

    @SerializedName(RELATED_EXERCISE_ENTRY_ID)
    val relatedExerciseEntryId: String = EMPTY,

    @SerializedName(TIME)
    val time: Int? = -1,

    @SerializedName(COMMENTS)
    val comments: String? = EMPTY
)