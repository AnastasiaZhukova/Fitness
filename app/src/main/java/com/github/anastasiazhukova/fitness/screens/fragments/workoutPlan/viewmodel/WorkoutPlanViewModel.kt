package com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.anastasiazhukova.fitness.R
import com.github.anastasiazhukova.fitness.authentication.user.IUserIdHolder
import com.github.anastasiazhukova.fitness.chat.domain.ChatParams
import com.github.anastasiazhukova.fitness.chat.utils.getChatId
import com.github.anastasiazhukova.fitness.domain.workoutPlan.WorkoutPlanModel
import com.github.anastasiazhukova.fitness.screens.activities.workout.domain.params.WorkoutParams
import com.github.anastasiazhukova.fitness.screens.activities.workout.domain.params.toWorkoutParams
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.presentation.WorkoutPlanUiState
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.usecase.trainerInfo.ITrainerInfoUseCase
import com.github.anastasiazhukova.fitness.screens.fragments.workoutPlan.usecase.workoutPlan.IWorkoutPlanUseCase
import com.github.anastasiazhukova.fitness.utils.Result
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.*

class WorkoutPlanViewModel(
    private val userIdHolder: IUserIdHolder,
    private val workoutPlanUseCase: IWorkoutPlanUseCase,
    private val trainerInfoUseCase: ITrainerInfoUseCase
) : ViewModel() {

    private val coroutineScopeJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + coroutineScopeJob)

    private val _workoutPlanLiveData = MutableLiveData<WorkoutPlanUiState>()
    private var workoutPlanModel: WorkoutPlanModel? = null
    private var trainerNickname: String? = null

    val workoutPlanLiveData: LiveData<WorkoutPlanUiState>
        get() = _workoutPlanLiveData

    fun get() = coroutineScope.launch {
        _workoutPlanLiveData.value = WorkoutPlanUiState.Loading

        val workoutPlanAsync =
            async { workoutPlanUseCase.get(MaterialDatePicker.todayInUtcMilliseconds()) }
        val trainerNicknameAsync = async { trainerInfoUseCase.getTrainerNickname() }

        val workoutPlanResult = workoutPlanAsync.await()
        val trainerNicknameResult = trainerNicknameAsync.await()

        when (workoutPlanResult) {
            is Result.Success -> {
                workoutPlanModel = workoutPlanResult.value
                _workoutPlanLiveData.value = WorkoutPlanUiState.Success(workoutPlanResult.value)
            }
            is Result.Error -> {
                _workoutPlanLiveData.value = WorkoutPlanUiState.Error(workoutPlanResult.exception)
            }
        }

        trainerNickname = when (trainerNicknameResult) {
            is Result.Success -> {
                trainerNicknameResult.value
            }
            is Result.Error -> {
                null
            }
        }
    }

    fun getWorkoutParams(): WorkoutParams? = workoutPlanModel?.toWorkoutParams()

    fun getChatParams(context: Context): ChatParams? {
        val currentUserId = userIdHolder.getCurrentUserId()

        return if (!currentUserId.isNullOrEmpty() && !trainerNickname.isNullOrEmpty()) {
            ChatParams(
                getChatId(
                    trainerId = trainerNickname!!,
                    userId = currentUserId
                ),
                context.resources.getString(R.string.trainer)
            )
        } else {
            null
        }
    }

    override fun onCleared() {
        super.onCleared()

        coroutineScopeJob.cancel()
    }
}