package com.github.anastasiazhukova.fitness.admin.screens.fragment.clients

import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.datasource.clients.ClientsDataSource
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.datasource.clients.IClientsDataSource
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.datasource.trainerInfo.ITrainerNicknameDataSource
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.datasource.trainerInfo.TrainerNicknameDataSource
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.domain.ClientsDataModelMapper
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.presentation.ClientsFragment
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.usecase.ClientsUseCase
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.usecase.IClientsUseCase
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.viewmodel.ClientsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ClientsScreenDependency {
    val module = module {
        scope<ClientsFragment> {
            scoped<ITrainerNicknameDataSource> {
                TrainerNicknameDataSource(
                    userIdHolder = get(),
                    trainerInfoDao = get()
                )
            }

            scoped<IClientsDataSource> {
                ClientsDataSource(
                    userInfoDao = get(),
                    clientDataModelMapper = ClientsDataModelMapper()
                )
            }

            scoped<IClientsUseCase> {
                ClientsUseCase(
                    trainerNicknameDataSource = get(),
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