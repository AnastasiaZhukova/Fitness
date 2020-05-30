package com.github.anastasiazhukova.fitness.screens.fragments.register

import com.github.anastasiazhukova.fitness.admin.screens.fragment.register.datasource.ITrainerInfoDataSource
import com.github.anastasiazhukova.fitness.admin.screens.fragment.register.datasource.TrainerInfoDataSource
import com.github.anastasiazhukova.fitness.admin.screens.fragment.register.domain.TrainerInfoModelDataMapper
import com.github.anastasiazhukova.fitness.admin.screens.fragment.register.presentation.RegisterFragment
import com.github.anastasiazhukova.fitness.admin.screens.fragment.register.usecase.register.IRegisterUseCase
import com.github.anastasiazhukova.fitness.admin.screens.fragment.register.usecase.register.RegisterUseCase
import com.github.anastasiazhukova.fitness.admin.screens.fragment.register.usecase.saveuser.ISaveUserUseCase
import com.github.anastasiazhukova.fitness.admin.screens.fragment.register.usecase.saveuser.SaveUserUseCase
import com.github.anastasiazhukova.fitness.admin.screens.fragment.register.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object RegisterScreenDependency {
    val module = module {
        scope<RegisterFragment> {
            scoped<ITrainerInfoDataSource> {
                TrainerInfoDataSource(
                    trainerInfoDao = get(),
                    mapper = TrainerInfoModelDataMapper()
                )
            }

            scoped<IRegisterUseCase> {
                RegisterUseCase(
                    authenticationManager = get()
                )
            }
            scoped<ISaveUserUseCase> {
                SaveUserUseCase(
                    dataSource = get()
                )
            }

            viewModel {
                RegisterViewModel(
                    registerUseCase = get(),
                    saveUserUseCase = get()
                )
            }
        }
    }
}