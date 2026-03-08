package com.gigboard.feature.trips

import com.gigboard.core.model.Trip

data class TripsUiState(
    val appFilters: List<String> = listOf("All apps"),
    val allTrips: List<Trip> = emptyList(),
    val visibleTrips: List<Trip> = emptyList(),
    val selectedApp: String = "All apps",
    val selectedScope: TripScope = TripScope.Day,
    val selectedDay: String = "Mar 6, 2026",
    val selectedWeek: String = "Mar 2 – Mar 8",
    val selectedMonth: String = "March 2026",
    val selectedYear: String = "2026",
    val filtersExpanded: Boolean = false,
    val expandedTripId: Long? = null,
) {
    val currentScopeValue: String
        get() = when (selectedScope) {
            TripScope.Day -> selectedDay
            TripScope.Week -> selectedWeek
            TripScope.Month -> selectedMonth
            TripScope.Year -> selectedYear
        }
}

enum class TripScope(val label: String) {
    Day("Day"),
    Week("Week"),
    Month("Month"),
    Year("Year"),
}

sealed interface TripsAction {
    data class SelectApp(val app: String) : TripsAction
    data class SelectScope(val scope: TripScope) : TripsAction
    data class ToggleTripExpansion(val tripId: Long) : TripsAction
    data object ToggleFilters : TripsAction
    data object StepScopeValuePrevious : TripsAction
    data object StepScopeValueNext : TripsAction
    data object ClearFilters : TripsAction
}
