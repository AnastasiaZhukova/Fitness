package com.github.anastasiazhukova.fitness.screens.fragments.zold.settings.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.anastasiazhukova.fitness.screens.fragments.zold.food.domain.TodayStatisticsModel
import com.github.anastasiazhukova.fitness.screens.fragments.zold.food.usecase.ITodayStatisticsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FoodViewModel(
    private val todayStatisticsUseCase: ITodayStatisticsUseCase
) : ViewModel() {

    private val coroutineScopeJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + coroutineScopeJob)

    private val _todayStatisticsLiveData = MutableLiveData<TodayStatisticsModel>()

    val todayStatisticsLiveData: LiveData<TodayStatisticsModel>
        get() = _todayStatisticsLiveData

    fun loadTodayStatistics() = coroutineScope.launch {
        val model = todayStatisticsUseCase.load()
        _todayStatisticsLiveData.value = model
    }

    override fun onCleared() {
        super.onCleared()

        coroutineScopeJob.cancel()
    }
}