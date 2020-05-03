package com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard

import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.datasource.mygoal.IMyGoalRepository
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.datasource.mygoal.StubMyGoalRepository
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.datasource.statistics.general.IGeneralStatisticsRepository
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.datasource.statistics.general.StubGeneralStatisticsRepository
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.datasource.statistics.today.ITodayStatisticsRepository
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.datasource.statistics.today.StubTodayStatisticsRepository
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.datasource.workout.INextWorkoutRepository
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.datasource.workout.StubNextWorkoutRepository
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.presentation.DashboardFragment
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.usecase.dashboard.DashboardUseCase
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.usecase.dashboard.IDashboardUseCase
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.viewmodel.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DashboardScreenDependency {
    val module = module {
        scope<DashboardFragment> {
            scoped<INextWorkoutRepository> { StubNextWorkoutRepository() }
            scoped<IMyGoalRepository> { StubMyGoalRepository() }
            scoped<ITodayStatisticsRepository> { StubTodayStatisticsRepository() }
            scoped<IGeneralStatisticsRepository> { StubGeneralStatisticsRepository() }

            scoped<IDashboardUseCase> {
                DashboardUseCase(
                    nextWorkoutRepository = get(),
                    myGoalRepository = get(),
                    todayStatisticsRepository = get(),
                    generalStatisticsRepository = get()
                )
            }


            viewModel { DashboardViewModel(get()) }
        }
    }
}