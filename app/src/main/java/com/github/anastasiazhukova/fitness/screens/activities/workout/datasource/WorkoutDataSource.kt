package com.github.anastasiazhukova.fitness.screens.activities.workout.datasource

import android.util.Log
import com.github.anastasiazhukova.fitness.datasource.exercise.IExerciseDao
import com.github.anastasiazhukova.fitness.screens.activities.workout.domain.model.ExerciseEntry
import com.github.anastasiazhukova.fitness.screens.activities.workout.domain.model.WorkoutModel
import com.github.anastasiazhukova.fitness.screens.activities.workout.domain.params.WorkoutParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkoutDataSource(
    private val exerciseDao: IExerciseDao
) : IWorkoutDataSource {

    val TAG = "DebugTag WorkoutDataSource";

    override suspend fun load(workoutParams: WorkoutParams): WorkoutModel? =
        withContext(Dispatchers.IO) {
            val entries: List<ExerciseEntry> = workoutParams.entries.mapNotNull { params ->
                try {
                    val dataModel = exerciseDao.get(params.relatedExerciseId)

                    if (dataModel != null) {
                        return@mapNotNull ExerciseEntry(
                            id = params.id,
                            name = dataModel.name,
                            description = dataModel.description,
                            time = params.time,
                            comments = params.comments
                        )
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "load: ", e)
                }

                null
            }

            WorkoutModel(entries)
        }
}