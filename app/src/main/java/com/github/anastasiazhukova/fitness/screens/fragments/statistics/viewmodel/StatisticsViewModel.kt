package com.github.anastasiazhukova.fitness.screens.fragments.statistics.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.anastasiazhukova.fitness.screens.fragments.statistics.domain.StatisticsPageModel
import com.github.anastasiazhukova.fitness.screens.fragments.statistics.usecase.IStatisticsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class StatisticsViewModel(
    private val statisticsUseCase: IStatisticsUseCase
) : ViewModel() {

    private val coroutineScopeJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + coroutineScopeJob)

    private val _statisticsLiveData = MutableLiveData<StatisticsPageModel>()

    val statisticsLiveData: LiveData<StatisticsPageModel>
        get() = _statisticsLiveData

    fun load() = coroutineScope.launch {
        val statisticsPageModel = statisticsUseCase.load()
        _statisticsLiveData.value = statisticsPageModel
    }

    override fun onCleared() {
        super.onCleared()

        coroutineScopeJob.cancel()
    }
}