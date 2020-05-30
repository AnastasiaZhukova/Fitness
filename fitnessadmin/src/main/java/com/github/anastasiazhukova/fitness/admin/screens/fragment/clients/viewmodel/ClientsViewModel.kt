package com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.domain.ClientModel
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.presentation.ClientsUiState
import com.github.anastasiazhukova.fitness.admin.screens.fragment.clients.usecase.IClientsUseCase
import com.github.anastasiazhukova.fitness.utils.Result

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ClientsViewModel(
    private val clientsUseCase: IClientsUseCase
) : ViewModel() {

    private val coroutineScopeJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + coroutineScopeJob)

    private val _clientsLiveData = MutableLiveData<ClientsUiState>()
    private var clientsModel: List<ClientModel> = emptyList()

    val clientsLiveData: LiveData<ClientsUiState>
        get() = _clientsLiveData

    fun load() = coroutineScope.launch {
        _clientsLiveData.value = ClientsUiState.Loading

        when (val result = clientsUseCase.load()) {
            is Result.Success -> {
                clientsModel = result.value
                _clientsLiveData.value = ClientsUiState.Success(clientsModel)
            }
            is Result.Error -> {
                _clientsLiveData.value = ClientsUiState.Error(result.exception)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

        coroutineScopeJob.cancel()
    }
}