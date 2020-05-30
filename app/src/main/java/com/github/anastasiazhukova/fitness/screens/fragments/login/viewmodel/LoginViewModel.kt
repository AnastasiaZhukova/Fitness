package com.github.anastasiazhukova.fitness.screens.fragments.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.anastasiazhukova.fitness.screens.fragments.login.presentation.LoginUiState
import com.github.anastasiazhukova.fitness.screens.fragments.login.usecase.ILoginUseCase
import com.github.anastasiazhukova.fitness.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: ILoginUseCase
) : ViewModel() {

    private val coroutineScopeJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + coroutineScopeJob)

    private val _authResultLiveData = MutableLiveData<LoginUiState>()

    val authenticationResultLiveData: LiveData<LoginUiState>
        get() = _authResultLiveData

    fun login(email: String, password: String) = coroutineScope.launch {
        _authResultLiveData.value = LoginUiState.Loading

        val result = loginUseCase.login(email, password)

        when (result) {
            is Result.Success -> _authResultLiveData.value = LoginUiState.Success
            is Result.Error -> _authResultLiveData.value = LoginUiState.Error
        }
    }

    override fun onCleared() {
        super.onCleared()

        coroutineScopeJob.cancel()
    }
}