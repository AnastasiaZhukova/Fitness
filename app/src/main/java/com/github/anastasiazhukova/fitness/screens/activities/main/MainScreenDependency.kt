package com.github.anastasiazhukova.fitness.screens.activities.main

import com.github.anastasiazhukova.fitness.domain.weight.WeightDataModelMapper
import com.github.anastasiazhukova.fitness.domain.weight.WeightModelDataMapper
import com.github.anastasiazhukova.fitness.screens.activities.main.datasource.userInfo.IUserInfoDataSource
import com.github.anastasiazhukova.fitness.screens.activities.main.datasource.userInfo.UserInfoDataSource
import com.github.anastasiazhukova.fitness.screens.activities.main.datasource.weight.IWeightDataSource
import com.github.anastasiazhukova.fitness.screens.activities.main.datasource.weight.WeightDataSource
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

            scoped<IUserInfoDataSource> {
                UserInfoDataSource(
                    userIdHolder = get(),
                    userInfoDao = get()
                )
            }

            scoped<IWeightUseCase> {
                WeightUseCase(
                    weightDataSource = get(),
                    userInfoDataSource = get()
                )
            }

            viewModel {
                MainViewModel(
                    userIdHolder = get(),
                    authenticationManager = get(),
                    weightUseCase = get()
                )
            }
        }
    }
}