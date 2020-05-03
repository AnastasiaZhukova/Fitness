package com.github.anastasiazhukova.fitness.screens.activities.authentication

import com.github.anastasiazhukova.fitness.screens.activities.authentication.presentation.AuthenticationActivity
import com.github.anastasiazhukova.fitness.screens.activities.authentication.usecase.login.ILoginUseCase
import com.github.anastasiazhukova.fitness.screens.activities.authentication.usecase.login.LoginUseCase
import com.github.anastasiazhukova.fitness.screens.activities.authentication.usecase.register.IRegisterUseCase
import com.github.anastasiazhukova.fitness.screens.activities.authentication.usecase.register.RegisterUseCase
import com.github.anastasiazhukova.fitness.screens.activities.authentication.viewmodel.AuthenticationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AuthenticationScreenDependency {
    val module = module {
        scope<AuthenticationActivity> {
            scoped<ILoginUseCase> {
                LoginUseCase(
                    authenticationManager = get()
                )
            }
            scoped<IRegisterUseCase> {
                RegisterUseCase(
                    authenticationManager = get()
                )
            }

            viewModel {
                AuthenticationViewModel(
                    loginUseCase = get(),
                    registerUseCase = get()
                )
            }
        }
    }
}