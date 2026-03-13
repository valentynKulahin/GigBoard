package com.gigboard.feature.trips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import com.gigboard.core.domain.GetTripsSummaryUseCase
import kotlinx.coroutines.flow.catch

@HiltViewModel
class TripsViewModel @Inject constructor(
    getTripsSummary: GetTripsSummaryUseCase,
) : ViewModel() {

    val uiState: StateFlow<TripsUiState> = getTripsSummary()
        .map<_, TripsUiState> { TripsUiState.Success(it) }
        .catch { emit(TripsUiState.Error(it.message ?: "Unknown error")) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TripsUiState.Loading,
        )

}
