package com.github.anastasiazhukova.fitness.domain.workoutPlan

import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.WorkoutPlanDataModel
import com.github.anastasiazhukova.fitness.utils.IMapper

class WorkoutPlanDataModelMapper : IMapper<WorkoutPlanDataModel, WorkoutPlanModel?> {
    override fun invoke(data: WorkoutPlanDataModel): WorkoutPlanModel? {
        val exerciseEntries = data.exerciseEntries.mapNotNull { entry ->
            if (entry.id.isNotEmpty() && entry.time != null) {
                ExerciseEntry(
                    id = entry.id,
                    relatedExerciseId = entry.relatedExerciseEntryId,
                    timeInMillis = entry.time,
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
                entries = exerciseEntries
            )
        } else {
            null
        }
    }
}