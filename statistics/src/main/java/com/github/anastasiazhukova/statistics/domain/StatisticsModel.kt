package com.github.anastasiazhukova.statistics.domain

data class StatisticsModel(
    val label: String,
    val values: List<StatisticsEntry> = emptyList()
)