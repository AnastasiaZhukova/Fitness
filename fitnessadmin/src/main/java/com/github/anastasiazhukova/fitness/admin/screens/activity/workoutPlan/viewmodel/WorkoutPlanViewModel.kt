package com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.anastasiazhukova.fitness.domain.workoutPlan.ExerciseEntry
import com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.domain.WorkoutPlanPageModel
import com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.presentation.WorkoutPlanScreenUiState
import com.github.anastasiazhukova.fitness.admin.screens.activity.workoutPlan.usecase.IWorkoutPlanUseCase
import com.github.anastasiazhukova.fitness.admin.screens.common.clientDetails.ClientDetailsParams
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

    private val _workoutPlanLiveData = MutableLiveData<WorkoutPlanScreenUiState>()
    private var clientDetailsParams: ClientDetailsParams? = null
    private var workoutPlanPageModel: WorkoutPlanPageModel? = null
    private var loadJob: Job? = null

    val workoutPlanLiveData: LiveData<WorkoutPlanScreenUiState>
        get() = _workoutPlanLiveData
    var currentlySelectedDate: Long = MaterialDatePicker.todayInUtcMilliseconds()
        private set

    fun setUser(clientDetailsParams: ClientDetailsParams) {
        this.clientDetailsParams = clientDetailsParams
    }

    fun get(date: Long) {
        clientDetailsParams?.let { clientDetailsParams ->
            if (currentlySelectedDate != date || workoutPlanPageModel == null) {
                currentlySelectedDate = date
                loadJob = coroutineScope.launch {
                    _workoutPlanLiveData.value = WorkoutPlanScreenUiState.Loading
                    val result =
                        workoutPlanUseCase.get(clientDetailsParams.id, currentlySelectedDate)

                    pushResult(result)
                }
            }
        }
    }

    fun getExercisesList() = workoutPlanPageModel?.exercises

    fun add(exerciseEntry: ExerciseEntry) {
        clientDetailsParams?.let { clientDetailsParams ->
            coroutineScope.launch {
                workoutPlanPageModel?.workoutPlanModel?.let {
                    _workoutPlanLiveData.value = WorkoutPlanScreenUiState.OperationInProgress
                    val result = workoutPlanUseCase.add(clientDetailsParams.id, it, exerciseEntry)

                    pushResult(result)
                }
            }
        }
    }

    fun update(exerciseEntry: ExerciseEntry) {
        clientDetailsParams?.let { clientDetailsParams ->
            coroutineScope.launch {
                workoutPlanPageModel?.workoutPlanModel?.let {
                    _workoutPlanLiveData.value = WorkoutPlanScreenUiState.OperationInProgress
                    val result = workoutPlanUseCase.update(clientDetailsParams.id, it, exerciseEntry)

                    pushResult(result)
                }
            }
        }
    }

    fun delete(exerciseEntry: ExerciseEntry) {
        clientDetailsParams?.let { clientDetailsParams ->
            coroutineScope.launch {
                workoutPlanPageModel?.workoutPlanModel?.let {
                    _workoutPlanLiveData.value = WorkoutPlanScreenUiState.OperationInProgress
                    val result =
                        workoutPlanUseCase.delete(clientDetailsParams.id, it, exerciseEntry)

                    pushResult(result)
                }
            }
        }
    }

    private fun pushResult(result: Result<WorkoutPlanPageModel>) =
        when (result) {
            is Result.Success -> {
                workoutPlanPageModel = result.value
                _workoutPlanLiveData.value = WorkoutPlanScreenUiState.Success(workoutPlanPageModel)
            }
            is Result.Error -> {
                _workoutPlanLiveData.value = WorkoutPlanScreenUiState.Error(result.exception)
            }
        }

    override fun onCleared() {
        super.onCleared()

        coroutineScopeJob.cancel()
    }
}