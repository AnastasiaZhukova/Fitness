package com.github.anastasiazhukova.fitness.domain.water

import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY

data class WaterModel(
    val id: String = EMPTY,
    val date: Long = -1,
    val entries: List<WaterEntry> = emptyList()
)

data class WaterEntry(
    val id: String = EMPTY,
    val type: String = EMPTY,
    val amount: Int = -1
)