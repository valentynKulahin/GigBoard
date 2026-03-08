package com.gigboard.feature.home

import com.gigboard.core.model.DailySummary

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val summary: DailySummary) : HomeUiState
    data class Error(val message: String) : HomeUiState
}
