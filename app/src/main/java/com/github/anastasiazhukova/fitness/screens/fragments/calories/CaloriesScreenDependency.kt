package com.github.anastasiazhukova.fitness.screens.fragments.calories

import com.github.anastasiazhukova.fitness.screens.fragments.calories.datasource.CaloriesDataSource
import com.github.anastasiazhukova.fitness.screens.fragments.calories.datasource.ICaloriesDataSource
import com.github.anastasiazhukova.fitness.domain.calories.CaloriesDataModelMapper
import com.github.anastasiazhukova.fitness.domain.calories.CaloriesModelDataMapper
import com.github.anastasiazhukova.fitness.screens.fragments.calories.presentation.CaloriesFragment
import com.github.anastasiazhukova.fitness.screens.fragments.calories.usecase.CaloriesUseCase
import com.github.anastasiazhukova.fitness.screens.fragments.calories.usecase.ICaloriesUseCase
import com.github.anastasiazhukova.fitness.screens.fragments.calories.viewmodel.CaloriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object CaloriesScreenDependency {
    val module = module {
        scope<CaloriesFragment> {
            scoped<ICaloriesDataSource> {
                CaloriesDataSource(
                    userIdHolder = get(),
                    caloriesDao = get(),
                    caloriesDataModelMapper = CaloriesDataModelMapper(),
                    caloriesModelDataMapper = CaloriesModelDataMapper()
                )
            }

            scoped<ICaloriesUseCase> {
                CaloriesUseCase(
                    caloriesDataSource = get()
                )
            }

            viewModel {
                CaloriesViewModel(
                    caloriesUseCase = get()
                )
            }
        }
    }
}