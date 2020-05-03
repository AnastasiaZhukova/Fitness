package com.github.anastasiazhukova.fitness.screens.activities.authentication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.anastasiazhukova.fitness.screens.activities.authentication.usecase.login.ILoginUseCase
import com.github.anastasiazhukova.fitness.screens.activities.authentication.usecase.register.IRegisterUseCase
import com.github.anastasiazhukova.fitness.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AuthenticationViewModel(
    private val loginUseCase: ILoginUseCase,
    private val registerUseCase: IRegisterUseCase
) : ViewModel() {

    private val coroutineScopeJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + coroutineScopeJob)

    private val _authResultLiveData = MutableLiveData<Result<Boolean>>()

    val authenticationResultLiveData: LiveData<Result<Boolean>>
        get() = _authResultLiveData

    fun login(email: String, password: String) = coroutineScope.launch {
        _authResultLiveData.value = loginUseCase.login(email, password)
    }

    fun register(email: String, password: String) = coroutineScope.launch {
        _authResultLiveData.value = registerUseCase.register(email, password)
    }

    override fun onCleared() {
        super.onCleared()

        coroutineScopeJob.cancel()
    }
}