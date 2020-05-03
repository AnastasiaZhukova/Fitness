package com.github.anastasiazhukova.fitness.screens.fragments.water

import com.github.anastasiazhukova.fitness.screens.fragments.water.datasource.IWaterDataSource
import com.github.anastasiazhukova.fitness.screens.fragments.water.datasource.WaterDataSource
import com.github.anastasiazhukova.fitness.screens.fragments.water.domain.WaterDataModelMapper
import com.github.anastasiazhukova.fitness.screens.fragments.water.domain.WaterModelDataMapper
import com.github.anastasiazhukova.fitness.screens.fragments.water.presentation.WaterFragment
import com.github.anastasiazhukova.fitness.screens.fragments.water.usecase.IWaterUseCase
import com.github.anastasiazhukova.fitness.screens.fragments.water.usecase.WaterUseCase
import com.github.anastasiazhukova.fitness.screens.fragments.water.viewmodel.WaterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object WaterScreenDependency {
    val module = module {
        scope<WaterFragment> {
            scoped<IWaterDataSource> {
                WaterDataSource(
                    userIdHolder = get(),
                    waterDao = get(),
                    waterDataModelMapper = WaterDataModelMapper(),
                    waterModelDataMapper = WaterModelDataMapper()
                )
            }

            scoped<IWaterUseCase> {
                WaterUseCase(
                    waterDataSource = get()
                )
            }

            viewModel {
                WaterViewModel(
                    waterUseCase = get()
                )
            }
        }
    }
}