package com.github.anastasiazhukova.fitness.datasource.exercise

import com.github.anastasiazhukova.fitness.datasource.exercise.ExerciseDaoConstants.DESCRIPTION
import com.github.anastasiazhukova.fitness.datasource.exercise.ExerciseDaoConstants.NAME
import com.github.anastasiazhukova.fitness.datasource.user.calories.CaloriesDaoConstants.ID
import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY
import com.google.gson.annotations.SerializedName

data class ExerciseDataModel(
    @SerializedName(ID)
    val id: String = EMPTY,

    @SerializedName(NAME)
    val name: String = EMPTY,

    @SerializedName(DESCRIPTION)
    val description: String = EMPTY
)