package com.github.anastasiazhukova.fitness.domain.workoutPlan

import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.ExerciseEntryDataModel
import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.WorkoutPlanDataModel
import com.github.anastasiazhukova.fitness.utils.IMapper

class WorkoutPlanModelDataMapper : IMapper<WorkoutPlanModel, WorkoutPlanDataModel> {
    override fun invoke(workoutPlanModel: WorkoutPlanModel): WorkoutPlanDataModel {
        val exerciseEntries = workoutPlanModel.entries.map { entry ->
            ExerciseEntryDataModel(
                id = entry.id,
                relatedExerciseEntryId = entry.relatedExerciseId,
                time = entry.timeInMillis,
                comments = entry.comments
            )
        }

        return WorkoutPlanDataModel(
            id = workoutPlanModel.id,
            date = workoutPlanModel.date,
            preview = workoutPlanModel.preview,
            duration = workoutPlanModel.duration,
            calories = workoutPlanModel.calories,
            level = workoutPlanModel.level,
            exerciseEntries = exerciseEntries
        )
    }
}