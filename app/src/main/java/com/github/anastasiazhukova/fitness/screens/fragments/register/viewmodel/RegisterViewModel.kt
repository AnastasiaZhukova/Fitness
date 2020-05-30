package com.github.anastasiazhukova.fitness.screens.fragments.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.anastasiazhukova.fitness.screens.fragments.register.presentation.RegisterUiState
import com.github.anastasiazhukova.fitness.screens.fragments.register.presentation.UserInfoParams
import com.github.anastasiazhukova.fitness.screens.fragments.register.presentation.toUserInfo
import com.github.anastasiazhukova.fitness.screens.fragments.register.usecase.register.IRegisterUseCase
import com.github.anastasiazhukova.fitness.screens.fragments.register.usecase.saveuser.ISaveUserUseCase
import com.github.anastasiazhukova.fitness.utils.Result
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: IRegisterUseCase,
    private val saveUserUseCase: ISaveUserUseCase
) : ViewModel() {

    val TAG = "DebugTag RegisterViewModel";

    private val coroutineScopeJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + coroutineScopeJob)

    private val _authResultLiveData = MutableLiveData<RegisterUiState>()

    val authenticationResultLiveData: LiveData<RegisterUiState>
        get() = _authResultLiveData

    fun register(email: String, password: String, userInfoParams: UserInfoParams) =
        coroutineScope.launch {
            _authResultLiveData.value = RegisterUiState.Loading

            val result = registerUseCase.register(email, password)

            when (result) {
                is Result.Success -> {
                    val userId = result.value

                    if (userId != null) {
                        saveUser(userId, userInfoParams)
                    } else {
                        RegisterUiState.Error.Other
                    }
                }
                is Result.Error -> {
                    when (result.exception) {
                        is FirebaseAuthUserCollisionException -> _authResultLiveData.value =
                            RegisterUiState.Error.UserExists
                        else -> _authResultLiveData.value = RegisterUiState.Error.Other
                    }
                }
            }
        }

    private suspend fun saveUser(userId: String, userInfoParams: UserInfoParams) {
        val userInfo = userInfoParams.toUserInfo(userId)

        val result = saveUserUseCase.save(userInfo)

        when (result) {
            is Result.Success -> _authResultLiveData.value = RegisterUiState.Success
            is Result.Error -> _authResultLiveData.value = RegisterUiState.Error.Other
        }
    }

    override fun onCleared() {
        super.onCleared()

        coroutineScopeJob.cancel()
    }
}