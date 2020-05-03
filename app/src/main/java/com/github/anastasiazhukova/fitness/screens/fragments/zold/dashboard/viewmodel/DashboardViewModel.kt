package com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.domain.DashboardModel
import com.github.anastasiazhukova.fitness.screens.fragments.zold.dashboard.usecase.dashboard.IDashboardUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val dashboardUseCase: IDashboardUseCase
) : ViewModel() {

    private val coroutineScopeJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + coroutineScopeJob)

    private val _dashboardLiveData = MutableLiveData<DashboardModel>()

    val dashboardLiveData: LiveData<DashboardModel>
        get() = _dashboardLiveData

    fun load() = coroutineScope.launch {
        val dashboardModel = dashboardUseCase.load()
        _dashboardLiveData.value = dashboardModel
    }

    override fun onCleared() {
        super.onCleared()

        coroutineScopeJob.cancel()
    }
}