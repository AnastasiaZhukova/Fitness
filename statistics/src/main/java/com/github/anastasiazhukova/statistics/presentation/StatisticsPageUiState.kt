package com.github.anastasiazhukova.statistics.presentation

import com.github.anastasiazhukova.statistics.domain.StatisticsPageModel

sealed class StatisticsPageUiState {
    object Loading : StatisticsPageUiState()
    class Success(val model: StatisticsPageModel?) : StatisticsPageUiState()
    class Error(val e: Throwable) : StatisticsPageUiState()
}