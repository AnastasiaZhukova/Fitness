package com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.anastasiazhukova.fitness.screens.activities.workout.domain.params.WorkoutParams
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.domain.WorkoutPlanModel
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.domain.toWorkoutParams
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.presentation.WorkoutPlanUiState
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.usecase.IWorkoutPlanUseCase
import com.github.anastasiazhukova.fitness.utils.Result
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WorkoutPlanViewModel(
    private val workoutPlanUseCase: IWorkoutPlanUseCase
) : ViewModel() {

    private val coroutineScopeJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + coroutineScopeJob)

    private val _workoutPlanLiveData = MutableLiveData<WorkoutPlanUiState>()
    private var workoutPlanModel: WorkoutPlanModel? = null

    val workoutPlanLiveData: LiveData<WorkoutPlanUiState>
        get() = _workoutPlanLiveData

    fun get() = coroutineScope.launch {
        _workoutPlanLiveData.value = WorkoutPlanUiState.Loading

        when (val result = workoutPlanUseCase.get(MaterialDatePicker.todayInUtcMilliseconds())) {
            is Result.Success -> {
                workoutPlanModel = result.value
                _workoutPlanLiveData.value = WorkoutPlanUiState.Success(result.value)
            }
            is Result.Error -> {
                _workoutPlanLiveData.value = WorkoutPlanUiState.Error(result.exception)
            }
        }
    }

    fun getWorkoutParams(): WorkoutParams? = workoutPlanModel?.toWorkoutParams()

    override fun onCleared() {
        super.onCleared()

        coroutineScopeJob.cancel()
    }
}