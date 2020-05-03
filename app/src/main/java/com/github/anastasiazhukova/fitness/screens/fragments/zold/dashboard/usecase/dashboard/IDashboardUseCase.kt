package com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.usecase.dashboard

import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.domain.DashboardModel

interface IDashboardUseCase {
    suspend fun load(): DashboardModel
}