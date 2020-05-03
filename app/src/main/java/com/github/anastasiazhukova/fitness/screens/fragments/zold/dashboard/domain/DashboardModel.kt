package com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.domain

data class DashboardModel(
    val workout: Workout?,
    val myGoal: MyGoal?,
    val todayStatistics: Statistics?,
    val generalStatistics: Statistics?
)