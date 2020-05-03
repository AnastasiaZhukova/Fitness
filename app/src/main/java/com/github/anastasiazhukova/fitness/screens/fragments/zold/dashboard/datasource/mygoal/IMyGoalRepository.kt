package com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.datasource.mygoal

import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.domain.MyGoal

interface IMyGoalRepository {
    suspend fun load(): MyGoal?
}