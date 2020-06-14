package com.github.anastasiazhukova.fitness.screens.activities.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.anastasiazhukova.fitness.authentication.auth.IAuthenticationManager
import com.github.anastasiazhukova.fitness.authentication.user.IUserIdHolder
import com.github.anastasiazhukova.fitness.domain.weight.WeightModel
import com.github.anastasiazhukova.fitness.screens.activities.main.usecase.IWeightUseCase
import com.github.anastasiazhukova.fitness.utils.Result
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(
    private val userIdHolder: IUserIdHolder,
    private val authenticationManager: IAuthenticationManager,
    private val weightUseCase: IWeightUseCase
) : ViewModel() {

    private val coroutineScopeJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + coroutineScopeJob)

    private val _weightLiveData = MutableLiveData<WeightModel>()
    private var weightModel: WeightModel? = null

    val weightLiveData: LiveData<WeightModel>
        get() = _weightLiveData
    var todayDateDate: Long = MaterialDatePicker.todayInUtcMilliseconds()
        private set

    fun getWeight() {
        coroutineScope.launch {
            val result = weightUseCase.get(todayDateDate)


            pushResult(result)
        }
    }

    fun addWeight(weight: Float) {
        coroutineScope.launch {
            weightModel?.let {
                val result = weightUseCase.add(it.copy(weight = weight))

                pushResult(result)
            }
        }
    }

    fun getCurrentUserId() = userIdHolder.getCurrentUserId()

    fun logout() {
        authenticationManager.logout()
    }

    private fun pushResult(result: Result<WeightModel?>) {
        if (result is Result.Success) {
            weightModel = result.value
            val weight = weightModel?.weight ?: 0.0f

            if (weight <= 0.0f) {
                _weightLiveData.value = weightModel
            }
        }
    }


    override fun onCleared() {
        super.onCleared()

        coroutineScopeJob.cancel()
    }
}