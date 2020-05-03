package com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.datasource.workout

import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.domain.Workout

class StubNextWorkoutRepository : INextWorkoutRepository {

    override suspend fun load(): Workout? {
        //return Workout.Break("25.07.2020")
        return Workout.Ready(
            totalTime = "56 min",
            totalCalories = "314 cal"
        )
    }
}