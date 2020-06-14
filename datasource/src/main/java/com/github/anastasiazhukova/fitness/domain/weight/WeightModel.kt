package com.github.anastasiazhukova.fitness.domain.weight

import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY

data class WeightModel(
    val id: String = EMPTY,
    val date: Long = -1,
    val weight: Float = 0.0f
)