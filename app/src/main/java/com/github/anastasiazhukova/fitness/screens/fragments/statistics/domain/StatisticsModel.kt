package com.github.anastasiazhukova.fitness.screens.fragments.statistics.domain

data class StatisticsModel(
    val label: String,
    val values: List<StatisticsEntry> = emptyList()
)