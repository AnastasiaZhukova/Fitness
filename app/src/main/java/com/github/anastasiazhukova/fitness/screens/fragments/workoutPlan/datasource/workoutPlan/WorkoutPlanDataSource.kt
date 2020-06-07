package com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.datasource.workoutPlan

import com.github.anastasiazhukova.fitness.authentication.user.IUserIdHolder
import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.IWorkoutPlanDao
import com.github.anastasiazhukova.fitness.datasource.user.workoutPlan.WorkoutPlanDataModel
import com.github.anastasiazhukova.fitness.domain.workoutPlan.WorkoutPlanModel
import com.github.anastasiazhukova.fitness.utils.IMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkoutPlanDataSource(
    private val userIdHolder: IUserIdHolder,
    private val workoutPlanDao: IWorkoutPlanDao,
    private val workoutPlanDataModelMapper: IMapper<WorkoutPlanDataModel, WorkoutPlanModel?>
) : IWorkoutPlanDataSource {

    override suspend fun get(date: Long): WorkoutPlanModel? =
        withContext(Dispatchers.IO) {
            userIdHolder.getCurrentUserId()?.let { userId ->
                workoutPlanDao.get(userId, date)?.let { dataModel ->
                    return@withContext workoutPlanDataModelMapper.invoke(dataModel)
                }
            }

            null
        }

}