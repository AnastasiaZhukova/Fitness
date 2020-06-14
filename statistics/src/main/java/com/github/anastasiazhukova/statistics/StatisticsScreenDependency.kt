package com.github.anastasiazhukova.statistics

import com.github.anastasiazhukova.fitness.domain.calories.CaloriesDataModelMapper
import com.github.anastasiazhukova.fitness.domain.water.WaterDataModelMapper
import com.github.anastasiazhukova.statistics.datasource.calories.CaloriesStatisticsRepository
import com.github.anastasiazhukova.statistics.datasource.calories.ICaloriesStatisticsRepository
import com.github.anastasiazhukova.statistics.datasource.water.IWaterStatisticsRepository
import com.github.anastasiazhukova.statistics.datasource.water.WaterStatisticsRepository
import com.github.anastasiazhukova.statistics.datasource.weight.IWeightStatisticsRepository
import com.github.anastasiazhukova.statistics.datasource.weight.WeightStatisticsRepository
import com.github.anastasiazhukova.statistics.presentation.StatisticsFragment
import com.github.anastasiazhukova.statistics.usecase.IStatisticsUseCase
import com.github.anastasiazhukova.statistics.usecase.StatisticsUseCase
import com.github.anastasiazhukova.statistics.viewmodel.StatisticsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object StatisticsScreenDependency {
    val module = module {
        scope<StatisticsFragment> {
            scoped<ICaloriesStatisticsRepository> {
                CaloriesStatisticsRepository(
                    caloriesDao = get(),
                    caloriesDataModelMapper = CaloriesDataModelMapper()
                )
            }
            scoped<IWeightStatisticsRepository> {
                WeightStatisticsRepository(
                    weightDao = get()
                )
            }
            scoped<IWaterStatisticsRepository> {
                WaterStatisticsRepository(
                    waterDao = get(),
                    waterDataModelMapper = WaterDataModelMapper()
                )
            }

            scoped<IStatisticsUseCase> {
                StatisticsUseCase(
                    caloriesStatisticsRepository = get(),
                    weightStatisticsRepository = get(),
                    waterStatisticsRepository = get()
                )
            }

            viewModel { StatisticsViewModel(get()) }
        }
    }
}