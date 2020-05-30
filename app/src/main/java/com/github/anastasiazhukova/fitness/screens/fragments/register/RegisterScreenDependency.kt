package com.github.anastasiazhukova.fitness.screens.fragments.register

import com.github.anastasiazhukova.fitness.screens.fragments.register.datasource.IUserInfoDataSource
import com.github.anastasiazhukova.fitness.screens.fragments.register.datasource.UserInfoDataSource
import com.github.anastasiazhukova.fitness.screens.fragments.register.domain.UserInfoModelDataMapper
import com.github.anastasiazhukova.fitness.screens.fragments.register.presentation.RegisterFragment
import com.github.anastasiazhukova.fitness.screens.fragments.register.usecase.register.IRegisterUseCase
import com.github.anastasiazhukova.fitness.screens.fragments.register.usecase.register.RegisterUseCase
import com.github.anastasiazhukova.fitness.screens.fragments.register.usecase.saveuser.ISaveUserUseCase
import com.github.anastasiazhukova.fitness.screens.fragments.register.usecase.saveuser.SaveUserUseCase
import com.github.anastasiazhukova.fitness.screens.fragments.register.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object RegisterScreenDependency {
    val module = module {
        scope<RegisterFragment> {
            scoped<IUserInfoDataSource> {
                UserInfoDataSource(
                    userInfoDao = get(),
                    mapper = UserInfoModelDataMapper()
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