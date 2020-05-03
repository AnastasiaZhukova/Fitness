package com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.datasource.workout

import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.domain.Workout

interface INextWorkoutRepository {
    suspend fun load(): Workout?
}