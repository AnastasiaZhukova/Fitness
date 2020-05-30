package com.github.anastasiazhukova.fitness.admin.screens.fragment.clients

import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.datasource.ClientsDataSource
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.datasource.IClientsDataSource
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.domain.ClientsDataModelMapper
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.usecase.ClientsUseCase
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.usecase.IClientsUseCase
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.viewmodel.ClientsViewModel
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.presentation.ClientsFragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ClientsScreenDependency {
    val module = module {
        scope<ClientsFragment> {
            scoped<IClientsDataSource> {
                ClientsDataSource(
                    userIdHolder = get(),
                    userInfoDao = get(),
                    clientDataModelMapper = ClientsDataModelMapper()
                )
            }

            scoped<IClientsUseCase> {
                ClientsUseCase(
                    clientsDataSource = get()
                )
            }

            viewModel {
                ClientsViewModel(
                    clientsUseCase = get()
                )
            }
        }
    }
}