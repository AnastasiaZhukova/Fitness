package com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.domain

import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.ExerciseEntryDataModel
import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.WorkoutPlanDataModel
import com.github.anastasiazhukova.fitness.utils.IMapper

class WorkoutPlanModelDataMapper : IMapper<WorkoutPlanModel, WorkoutPlanDataModel> {
    override fun invoke(model: WorkoutPlanModel): WorkoutPlanDataModel {
        val entriesData = model.entries.map { entry ->
            ExerciseEntryDataModel(
                id = entry.id,
                time = entry.time,
                comments = entry.comments
            )
        }

        return WorkoutPlanDataModel(
            id = model.id,
            date = model.date,
            preview = model.preview,
            duration = model.duration,
            calories = model.calories,
            level = model.level,
            exerciseEntries = entriesData
        )
    }
}