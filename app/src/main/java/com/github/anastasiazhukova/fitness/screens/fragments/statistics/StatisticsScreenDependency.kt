package com.github.anastasiazhukova.fitness.screens.fragments.statistics

import com.github.anastasiazhukova.fitness.screens.fragments.statistics.datasource.calories.ICaloriesStatisticsRepository
import com.github.anastasiazhukova.fitness.screens.fragments.statistics.datasource.calories.StubCaloriesStatisticsRepository
import com.github.anastasiazhukova.fitness.screens.fragments.statistics.datasource.weight.IWeightStatisticsRepository
import com.github.anastasiazhukova.fitness.screens.fragments.statistics.datasource.weight.StubWeightStatisticsRepository
import com.github.anastasiazhukova.fitness.screens.fragments.statistics.datasource.workout.IWorkoutStatisticsRepository
import com.github.anastasiazhukova.fitness.screens.fragments.statistics.datasource.workout.StubWorkoutStatisticsRepository
import com.github.anastasiazhukova.fitness.screens.fragments.statistics.presentation.StatisticsFragment
import com.github.anastasiazhukova.fitness.screens.fragments.statistics.usecase.IStatisticsUseCase
import com.github.anastasiazhukova.fitness.screens.fragments.statistics.usecase.StatisticsUseCase
import com.github.anastasiazhukova.fitness.screens.fragments.statistics.viewmodel.StatisticsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object StatisticsScreenDependency {
    val module = module {
        scope<StatisticsFragment> {
            scoped<ICaloriesStatisticsRepository> { StubCaloriesStatisticsRepository() }
            scoped<IWeightStatisticsRepository> { StubWeightStatisticsRepository() }
            scoped<IWorkoutStatisticsRepository> { StubWorkoutStatisticsRepository() }

            scoped<IStatisticsUseCase> {
                StatisticsUseCase(
                    caloriesStatisticsRepository = get(),
                    weightStatisticsRepository = get(),
                    workoutStatisticsRepository = get()
                )
            }

            viewModel { StatisticsViewModel(get()) }
        }
    }
}