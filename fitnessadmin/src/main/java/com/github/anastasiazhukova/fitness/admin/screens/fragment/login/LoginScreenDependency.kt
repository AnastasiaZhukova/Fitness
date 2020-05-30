package com.github.anastasiazhukova.fitness.admin.screens.fragment.login

import com.github.anastasiazhukova.fitness.admin.screens.fragment.login.presentation.LoginFragment
import com.github.anastasiazhukova.fitness.admin.screens.fragment.login.usecase.ILoginUseCase
import com.github.anastasiazhukova.fitness.admin.screens.fragment.login.usecase.LoginUseCase
import com.github.anastasiazhukova.fitness.admin.screens.fragment.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object LoginScreenDependency {
    val module = module {
        scope<LoginFragment> {
            scoped<ILoginUseCase> {
                LoginUseCase(
                    authenticationManager = get()
                )
            }

            viewModel {
                LoginViewModel(
                    loginUseCase = get()
                )
            }
        }
    }
}