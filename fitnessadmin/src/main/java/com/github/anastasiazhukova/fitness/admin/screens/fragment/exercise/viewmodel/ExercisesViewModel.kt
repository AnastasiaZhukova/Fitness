package com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.domain.ExerciseModel
import com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.presentation.ExercisesScreenUiState
import com.github.anastasiazhukova.fitness.admin.screens.fragment.exercise.usecase.IExercisesUseCase
import com.github.anastasiazhukova.fitness.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ExercisesViewModel(
    private val exercisesUseCase: IExercisesUseCase
) : ViewModel() {

    private val coroutineScopeJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + coroutineScopeJob)

    private val _exercisesLiveData = MutableLiveData<ExercisesScreenUiState>()
    private var exercisesModel: List<ExerciseModel> = emptyList()
    private var loadJob: Job? = null

    val exercisesLiveData: LiveData<ExercisesScreenUiState>
        get() = _exercisesLiveData

    fun load() {
        loadJob = coroutineScope.launch {
            _exercisesLiveData.value = ExercisesScreenUiState.Loading
            val result = exercisesUseCase.getAll()

            pushResult(result)
        }
    }

    fun add(exerciseEntry: ExerciseModel) {
        coroutineScope.launch {
            _exercisesLiveData.value = ExercisesScreenUiState.OperationInProgress
            val result = exercisesUseCase.add(exerciseEntry)

            pushResult(result)
        }
    }

    fun update(exerciseEntry: ExerciseModel) {
        coroutineScope.launch {
            _exercisesLiveData.value = ExercisesScreenUiState.OperationInProgress
            val result = exercisesUseCase.update(exerciseEntry)

            pushResult(result)
        }
    }

    fun delete(exerciseEntry: ExerciseModel) {
        coroutineScope.launch {
            _exercisesLiveData.value = ExercisesScreenUiState.OperationInProgress
            val result = exercisesUseCase.delete(exerciseEntry)

            pushResult(result)
        }
    }

    private fun pushResult(result: Result<List<ExerciseModel>>) =
        when (result) {
            is Result.Success -> {
                exercisesModel = result.value
                _exercisesLiveData.value = ExercisesScreenUiState.Success(exercisesModel)
            }
            is Result.Error -> {
                _exercisesLiveData.value = ExercisesScreenUiState.Error(result.exception)
            }
        }

    override fun onCleared() {
        super.onCleared()

        coroutineScopeJob.cancel()
    }
}