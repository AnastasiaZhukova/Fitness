package com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.usecase

import com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.datasource.IWorkoutPlanDataSource
import com.github.anastasiazhukova.fitness.domain.workoutPlan.ExerciseEntry
import com.github.anastasiazhukova.fitness.domain.workoutPlan.WorkoutPlanModel
import com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.domain.WorkoutPlanPageModel
import com.github.anastasiazhukova.fitness.admin.screens.common.exercise.datasource.IExerciseDataSource
import com.github.anastasiazhukova.fitness.admin.screens.common.exercise.domain.ExerciseModel
import com.github.anastasiazhukova.fitness.utils.Result

class WorkoutPlanUseCase(
    private val workoutPlanDataSource: IWorkoutPlanDataSource,
    private val exerciseDataSource: IExerciseDataSource
) : IWorkoutPlanUseCase {

    private var cachedExercises: List<ExerciseModel> = emptyList()

    override suspend fun get(userId: String, date: Long): Result<WorkoutPlanPageModel> {
        return try {
            val exercises = getExercises()
            val workoutPlanModel = workoutPlanDataSource.get(userId, date)

            val mappedEntries =
                workoutPlanModel?.entries?.toMutableList()?.mapNotNull { exerciseEntry ->
                    val exerciseModel = exercises.find { it.id == exerciseEntry.relatedExerciseId }

                    if (exerciseModel != null && exerciseModel.name.isNotEmpty()) {
                        exerciseEntry.copy(
                            name = exerciseModel.name
                        )
                    } else {
                        null
                    }
                } ?: emptyList()

            val workoutPlanModelWithMappedEntries = workoutPlanModel?.copy(
                entries = mappedEntries
            )

            Result.Success(
                WorkoutPlanPageModel(
                    workoutPlanModel = workoutPlanModelWithMappedEntries,
                    exercises = exercises
                )
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun add(
        userId: String,
        workoutPlanModel: WorkoutPlanModel,
        exerciseEntry: ExerciseEntry
    ): Result<WorkoutPlanPageModel> =
        try {
            val exercises = getExercises()
            val newWorkoutPlanModel = workoutPlanDataSource.add(userId, workoutPlanModel, exerciseEntry)

            Result.Success(
                WorkoutPlanPageModel(
                    workoutPlanModel = newWorkoutPlanModel,
                    exercises = exercises
                )
            )
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun update(
        userId: String,
        workoutPlanModel: WorkoutPlanModel,
        exerciseEntry: ExerciseEntry
    ): Result<WorkoutPlanPageModel> =
        try {
            val exercises = getExercises()
            val newWorkoutPlanModel = workoutPlanDataSource.update(userId, workoutPlanModel, exerciseEntry)

            Result.Success(
                WorkoutPlanPageModel(
                    workoutPlanModel = newWorkoutPlanModel,
                    exercises = exercises
                )
            )
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun delete(
        userId: String,
        workoutPlanModel: WorkoutPlanModel,
        exerciseEntry: ExerciseEntry
    ): Result<WorkoutPlanPageModel> =
        try {
            val exercises = getExercises()
            val newWorkoutPlanModel =
                workoutPlanDataSource.delete(userId, workoutPlanModel, exerciseEntry)

            Result.Success(
                WorkoutPlanPageModel(
                    workoutPlanModel = newWorkoutPlanModel,
                    exercises = exercises
                )
            )
        } catch (e: Exception) {
            Result.Error(e)
        }

    private suspend fun getExercises(): List<ExerciseModel> =
        if (cachedExercises.isNotEmpty()) {
            cachedExercises
        } else {
            exerciseDataSource.getAll()
        }
}