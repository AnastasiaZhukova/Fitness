package com.github.anastasiazhukova.statistics.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.anastasiazhukova.fitness.utils.Result
import com.github.anastasiazhukova.statistics.domain.StatisticsPageParams
import com.github.anastasiazhukova.statistics.presentation.StatisticsPageUiState
import com.github.anastasiazhukova.statistics.usecase.IStatisticsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class StatisticsViewModel(
    private val statisticsUseCase: IStatisticsUseCase
) : ViewModel() {

    private val coroutineScopeJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + coroutineScopeJob)

    private var pageParams: StatisticsPageParams? = null
    private val _statisticsLiveData = MutableLiveData<StatisticsPageUiState>()

    val statisticsLiveData: LiveData<StatisticsPageUiState>
        get() = _statisticsLiveData

    fun load() = coroutineScope.launch {
        pageParams?.let { params ->
            _statisticsLiveData.value = StatisticsPageUiState.Loading

            val result = statisticsUseCase.load(params.clientId)

            when (result) {
                is Result.Success -> {
                    _statisticsLiveData.value = StatisticsPageUiState.Success(result.value)
                }
                is Result.Error -> {
                    _statisticsLiveData.value = StatisticsPageUiState.Error(result.exception)
                }
            }
        }
    }

    fun setParams(pageParams: StatisticsPageParams) {
        this.pageParams = pageParams
    }

    override fun onCleared() {
        super.onCleared()

        coroutineScopeJob.cancel()
    }
}