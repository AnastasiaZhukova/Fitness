package com.github.anastasiazhukova.fitness.screens.fragments.water.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.anastasiazhukova.fitness.domain.water.WaterEntry
import com.github.anastasiazhukova.fitness.domain.water.WaterModel
import com.github.anastasiazhukova.fitness.screens.fragments.water.presentation.WaterScreenUiState
import com.github.anastasiazhukova.fitness.screens.fragments.water.usecase.IWaterUseCase
import com.github.anastasiazhukova.fitness.utils.Result
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WaterViewModel(
    private val waterUseCase: IWaterUseCase
) : ViewModel() {

    private val coroutineScopeJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + coroutineScopeJob)

    private val _waterLiveData = MutableLiveData<WaterScreenUiState>()
    private var waterModel: WaterModel? = null
    private var loadJob: Job? = null

    val waterLiveData: LiveData<WaterScreenUiState>
        get() = _waterLiveData
    var currentlySelectedDate: Long = MaterialDatePicker.todayInUtcMilliseconds()
        private set

    fun get(date: Long) {
        if (currentlySelectedDate != date || waterModel == null ) {
            currentlySelectedDate = date
            loadJob = coroutineScope.launch {
                _waterLiveData.value = WaterScreenUiState.Loading
                val result = waterUseCase.get(currentlySelectedDate)

                pushResult(result)
            }
        }
    }

    fun add(waterEntry: WaterEntry) {
        coroutineScope.launch {
            waterModel?.let {
                _waterLiveData.value = WaterScreenUiState.OperationInProgress
                val result = waterUseCase.add(it, waterEntry)

                pushResult(result)
            }
        }
    }

    fun update(waterEntry: WaterEntry) {
        coroutineScope.launch {
            waterModel?.let {
                _waterLiveData.value = WaterScreenUiState.OperationInProgress
                val result = waterUseCase.update(it, waterEntry)

                pushResult(result)
            }
        }
    }

    fun delete(waterEntry: WaterEntry) {
        coroutineScope.launch {
            waterModel?.let {
                _waterLiveData.value = WaterScreenUiState.OperationInProgress
                val result = waterUseCase.delete(it, waterEntry)

                pushResult(result)
            }
        }
    }

    private fun pushResult(result: Result<WaterModel?>) =
        when (result) {
            is Result.Success -> {
                waterModel = result.value
                _waterLiveData.value = WaterScreenUiState.Success(waterModel)
            }
            is Result.Error -> {
                _waterLiveData.value = WaterScreenUiState.Error(result.exception)
            }
        }

    override fun onCleared() {
        super.onCleared()

        coroutineScopeJob.cancel()
    }
}