package com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.datasource

import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.domain.WorkoutPlanModel

interface IWorkoutPlanDataSource {
    suspend fun get(date: Long): WorkoutPlanModel?
}