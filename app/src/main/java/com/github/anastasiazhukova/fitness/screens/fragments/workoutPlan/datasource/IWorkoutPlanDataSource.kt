package com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.datasource

import com.github.anastasiazhukova.fitness.domain.workoutPlan.WorkoutPlanModel

interface IWorkoutPlanDataSource {
    suspend fun get(date: Long): WorkoutPlanModel?
}