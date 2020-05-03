package com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.domain

import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.WorkoutPlanDataModel
import com.github.anastasiazhukova.fitness.utils.IMapper

class WorkoutPlanDataModelMapper : IMapper<WorkoutPlanDataModel, WorkoutPlanModel?> {
    override fun invoke(data: WorkoutPlanDataModel): WorkoutPlanModel? {
        val entries = data.exerciseEntries.mapNotNull { entry ->
            if (entry.id.isNotEmpty() && entry.time != null) {
                ExerciseEntry(
                    id = entry.id,
                    time = entry.time,
                    comments = entry.comments
                )
            } else {
                null
            }
        }

        return if (data.id.isNotEmpty() && data.date != null) {
            WorkoutPlanModel(
                id = data.id,
                date = data.date,
                preview = data.preview,
                duration = data.duration,
                calories = data.calories,
                level = data.level,
                entries = entries
            )
        } else {
            null
        }
    }
}