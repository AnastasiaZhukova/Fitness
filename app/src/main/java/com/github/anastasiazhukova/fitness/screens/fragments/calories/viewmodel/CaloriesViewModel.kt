package com.github.anastasiazhukova.fitness.screens.fragments.calories.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.anastasiazhukova.fitness.domain.calories.CaloriesEntry
import com.github.anastasiazhukova.fitness.domain.calories.CaloriesModel
import com.github.anastasiazhukova.fitness.screens.fragments.calories.presentation.CaloriesScreenUiState
import com.github.anastasiazhukova.fitness.screens.fragments.calories.usecase.ICaloriesUseCase
import com.github.anastasiazhukova.fitness.utils.Result
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CaloriesViewModel(
    private val caloriesUseCase: ICaloriesUseCase
) : ViewModel() {

    private val coroutineScopeJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + coroutineScopeJob)

    private val _caloriesLiveData = MutableLiveData<CaloriesScreenUiState>()
    private var caloriesModel: CaloriesModel? = null
    private var loadJob: Job? = null

    val caloriesLiveData: LiveData<CaloriesScreenUiState>
        get() = _caloriesLiveData
    var currentlySelectedDate: Long = MaterialDatePicker.todayInUtcMilliseconds()
        private set

    fun get(date: Long) {
        if (currentlySelectedDate != date || caloriesModel == null) {
            currentlySelectedDate = date
            loadJob = coroutineScope.launch {
                _caloriesLiveData.value = CaloriesScreenUiState.Loading
                val result = caloriesUseCase.get(currentlySelectedDate)

                pushResult(result)
            }
        }
    }

    fun add(caloriesEntry: CaloriesEntry) {
        coroutineScope.launch {
            caloriesModel?.let {
                _caloriesLiveData.value = CaloriesScreenUiState.OperationInProgress
                val result = caloriesUseCase.add(it, caloriesEntry)

                pushResult(result)
            }
        }
    }

    fun delete(caloriesEntry: CaloriesEntry) {
        coroutineScope.launch {
            caloriesModel?.let {
                _caloriesLiveData.value = CaloriesScreenUiState.OperationInProgress
                val result = caloriesUseCase.delete(it, caloriesEntry)

                pushResult(result)
            }
        }
    }

    private fun pushResult(result: Result<CaloriesModel?>) =
        when (result) {
            is Result.Success -> {
                caloriesModel = result.value
                _caloriesLiveData.value = CaloriesScreenUiState.Success(caloriesModel)
            }
            is Result.Error -> {
                _caloriesLiveData.value = CaloriesScreenUiState.Error(result.exception)
            }
        }

    override fun onCleared() {
        super.onCleared()

        coroutineScopeJob.cancel()
    }
}