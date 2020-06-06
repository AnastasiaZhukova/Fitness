package com.github.anastasiazhukova.fitness.admin.screens.common.exercise.domain

import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY

data class ExerciseModel(
    val id: String = EMPTY,
    val authorId: String = EMPTY,
    val name: String = EMPTY,
    val description: String = EMPTY
)
