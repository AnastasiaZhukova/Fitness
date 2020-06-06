package com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.domain

import com.github.anastasiazhukova.fitness.admin.screens.common.exercise.domain.ExerciseModel
import com.github.anastasiazhukova.fitness.domain.workoutPlan.WorkoutPlanModel

data class WorkoutPlanPageModel(
    val workoutPlanModel: WorkoutPlanModel?,
    val exercises: List<ExerciseModel>
)