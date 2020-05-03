package com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.usecase.dashboard

import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.datasource.mygoal.IMyGoalRepository
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.datasource.statistics.general.IGeneralStatisticsRepository
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.datasource.statistics.today.ITodayStatisticsRepository
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.datasource.workout.INextWorkoutRepository
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.domain.DashboardModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class DashboardUseCase(
    private val nextWorkoutRepository: INextWorkoutRepository,
    private val myGoalRepository: IMyGoalRepository,
    private val todayStatisticsRepository: ITodayStatisticsRepository,
    private val generalStatisticsRepository: IGeneralStatisticsRepository
) : IDashboardUseCase {

    override suspend fun load(): DashboardModel {
        return coroutineScope {
            val workoutAsync = async { nextWorkoutRepository.load() }
            val myGoalAsync = async { myGoalRepository.load() }
            val todayStatisticsAsync = async { todayStatisticsRepository.load() }
            val generalStatisticsAsync = async { generalStatisticsRepository.load() }

            val workout = workoutAsync.await()
            val myGoal = myGoalAsync.await()
            val todayStatistics = todayStatisticsAsync.await()
            val generalStatistics = generalStatisticsAsync.await()

            DashboardModel(
                workout = workout,
                myGoal = myGoal,
                todayStatistics = todayStatistics,
                generalStatistics = generalStatistics
            )
        }
    }
}