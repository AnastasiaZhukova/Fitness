package com.github.anastasiazhukova.fitness.screens.activities.main

import com.github.anastasiazhukova.fitness.domain.weight.WeightDataModelMapper
import com.github.anastasiazhukova.fitness.domain.weight.WeightModelDataMapper
import com.github.anastasiazhukova.fitness.screens.activities.main.datasource.IWeightDataSource
import com.github.anastasiazhukova.fitness.screens.activities.main.datasource.WeightDataSource
import com.github.anastasiazhukova.fitness.screens.activities.main.presentation.MainActivity
import com.github.anastasiazhukova.fitness.screens.activities.main.usecase.IWeightUseCase
import com.github.anastasiazhukova.fitness.screens.activities.main.usecase.WeightUseCase
import com.github.anastasiazhukova.fitness.screens.activities.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MainScreenDependency {
    val module = module {
        scope<MainActivity> {
            scoped<IWeightDataSource> {
                WeightDataSource(
                    userIdHolder = get(),
                    weightDao = get(),
                    weightDataModelMapper = WeightDataModelMapper(),
                    weightModelDataMapper = WeightModelDataMapper()
                )
            }

            scoped<IWeightUseCase> {
                WeightUseCase(
                    weightDataSource = get()
                )
            }

            viewModel {
                MainViewModel(
                    userIdHolder = get(),
                    weightUseCase = get()
                )
            }
        }
    }
}