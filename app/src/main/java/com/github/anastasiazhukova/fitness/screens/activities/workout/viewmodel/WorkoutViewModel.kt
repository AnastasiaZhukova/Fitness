package com.github.anastasiazhukova.fitness.screens.activities.workout.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.anastasiazhukova.fitness.screens.activities.workout.domain.params.WorkoutParams
import com.github.anastasiazhukova.fitness.screens.activities.workout.presentation.WorkoutUiState
import com.github.anastasiazhukova.fitness.screens.activities.workout.usecase.IWorkoutUseCase
import com.github.anastasiazhukova.fitness.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WorkoutViewModel(
    private val workoutUseCase: IWorkoutUseCase
) : ViewModel() {

    private val coroutineScopeJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + coroutineScopeJob)

    private val _workoutLiveData = MutableLiveData<WorkoutUiState>()

    val workoutLiveData: LiveData<WorkoutUiState>
        get() = _workoutLiveData

    var isPaused = false
    var currentExerciseIndex = -1

    fun load(workoutParams: WorkoutParams) = coroutineScope.launch {
        _workoutLiveData.value = WorkoutUiState.Loading

        val result = workoutUseCase.load(workoutParams)

        when (result) {
            is Result.Success -> _workoutLiveData.value = WorkoutUiState.Success(result.value)
            is Result.Error -> _workoutLiveData.value = WorkoutUiState.Error(result.exception)
        }
    }

    override fun onCleared() {
        super.onCleared()

        coroutineScopeJob.cancel()
    }
}