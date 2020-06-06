package com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.datasource

import android.util.Log
import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.IWorkoutPlanDao
import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.WorkoutPlanDataModel
import com.github.anastasiazhukova.fitness.domain.workoutPlan.ExerciseEntry
import com.github.anastasiazhukova.fitness.domain.workoutPlan.WorkoutPlanModel
import com.github.anastasiazhukova.fitness.utils.IMapper
import com.github.anastasiazhukova.fitness.utils.extensions.generateId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkoutPlanDataSource(
    private val workoutPlanDao: IWorkoutPlanDao,
    private val workoutPlanDataModelMapper: IMapper<WorkoutPlanDataModel, WorkoutPlanModel?>,
    private val workoutPlanModelDataMapper: IMapper<WorkoutPlanModel, WorkoutPlanDataModel>
) : IWorkoutPlanDataSource {

    val TAG = "DebugTag WorkoutPlanDataSource";

    override suspend fun get(userId: String, date: Long): WorkoutPlanModel? =
        withContext(Dispatchers.IO) {
            val dataModel = workoutPlanDao.get(userId, date)

            Log.d(TAG, "get: dataModel = $dataModel")

            return@withContext if (dataModel == null) {
                WorkoutPlanModel(
                    id = generateId(),
                    date = date,
                    entries = emptyList()
                )
            } else {
                workoutPlanDataModelMapper.invoke(dataModel)
            }
        }

    override suspend fun add(
        userId: String,
        workoutPlanModel: WorkoutPlanModel,
        exerciseEntry: ExerciseEntry
    ): WorkoutPlanModel =
        withContext(Dispatchers.IO) {
            val newEntry = if (exerciseEntry.id.isEmpty()) {
                exerciseEntry.copy(
                    id = generateId()
                )
            } else {
                exerciseEntry
            }

            val newEntries = workoutPlanModel.entries.toMutableList().apply { add(newEntry) }
            val newModel = workoutPlanModel.copy(entries = newEntries)
            val mappedModel = workoutPlanModelDataMapper.invoke(newModel)

            workoutPlanDao.update(userId, mappedModel)

            return@withContext newModel
        }

    override suspend fun update(
        userId: String,
        workoutPlanModel: WorkoutPlanModel,
        exerciseEntry: ExerciseEntry
    ): WorkoutPlanModel =
        withContext(Dispatchers.IO) {
            val oldEntries = workoutPlanModel.entries
            val indexOfExercise = oldEntries.indexOfFirst { it.id == exerciseEntry.id }

            val newEntries = oldEntries.toMutableList().apply {
                set(indexOfExercise, exerciseEntry)
            }
            val newModel = workoutPlanModel.copy(entries = newEntries)
            val mappedModel = workoutPlanModelDataMapper.invoke(newModel)

            workoutPlanDao.update(userId, mappedModel)

            return@withContext newModel
        }

    override suspend fun delete(
        userId: String,
        workoutPlanModel: WorkoutPlanModel,
        exerciseEntry: ExerciseEntry
    ): WorkoutPlanModel =
        withContext(Dispatchers.IO) {
            val newEntries =
                workoutPlanModel.entries.toMutableList()
                    .apply { removeIf { it.id == exerciseEntry.id } }

            Log.d(
                TAG,
                "delete: newEntries = ${newEntries.count()} oldEntries = ${workoutPlanModel.entries.count()}"
            )

            val newModel = workoutPlanModel.copy(entries = newEntries)
            val mappedModel = workoutPlanModelDataMapper.invoke(newModel)

            workoutPlanDao.update(userId, mappedModel)

            return@withContext newModel
        }
}