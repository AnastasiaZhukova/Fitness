package com.github.anastasiazhukova.statistics.domain

import com.github.anastasiazhukova.fitness.domain.calories.CaloriesModel
import com.github.anastasiazhukova.fitness.domain.water.WaterModel

sealed class StatisticsEntry(
    val date: Long,
    val value: Float
) {
    class Weight(
        date: Long,
        value: Float
    ) : StatisticsEntry(date, value)

    class Calories(
        date: Long,
        value: Float,
        val model: CaloriesModel
    ) : StatisticsEntry(date, value)

    class Water(
        date: Long,
        value: Float,
        val model: WaterModel
    ) : StatisticsEntry(date, value)
}