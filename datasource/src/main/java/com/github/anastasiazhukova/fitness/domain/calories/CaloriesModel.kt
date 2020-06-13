package com.github.anastasiazhukova.fitness.domain.calories

import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY

data class CaloriesModel(
    val id: String = EMPTY,
    val date: Long = -1,
    val entries: List<CaloriesEntry> = emptyList()
)

data class CaloriesEntry(
    val id: String = EMPTY,
    val name: String = EMPTY,
    val calories: Int = -1,
    val weight: Int = -1
)