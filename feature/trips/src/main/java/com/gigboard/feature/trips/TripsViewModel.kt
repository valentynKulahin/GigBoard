package com.gigboard.feature.trips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gigboard.core.domain.repository.TripsRepository
import com.gigboard.core.model.GigPlatform
import com.gigboard.core.model.Trip
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class TripsViewModel @Inject constructor(
    repository: TripsRepository,
) : ViewModel() {

    private val reducer = TripsReducer()

    val uiState: StateFlow<TripsUiState> = repository
        .getTrips()
        .map { trips -> reducer.buildInitialState(trips) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TripsUiState(),
        )

    fun onAction(action: TripsAction) {
        // Intentionally left as next step if you already have a Store/Reducer pattern in project.
        // For preview/fake mode, UI can stay read-only with fake data source first.
    }
}

internal class TripsReducer {

    private val defaultDay = "Mar 6, 2026"
    private val defaultWeek = "Mar 2 – Mar 8"
    private val defaultMonth = "March 2026"
    private val defaultYear = "2026"

    fun buildInitialState(allTrips: List<Trip>): TripsUiState {
        val appFilters = buildList {
            add("All apps")
            addAll(
                allTrips.mapNotNull { it.platform }
                    .distinct()
                    .map(::platformLabel)
            )
        }

        val visibleTrips = filterTrips(
            allTrips = allTrips,
            selectedApp = "All apps",
            selectedScope = TripScope.Day,
            selectedDay = defaultDay,
            selectedWeek = defaultWeek,
            selectedMonth = defaultMonth,
            selectedYear = defaultYear,
        )

        return TripsUiState(
            appFilters = appFilters,
            allTrips = allTrips,
            visibleTrips = visibleTrips,
            selectedDay = defaultDay,
            selectedWeek = defaultWeek,
            selectedMonth = defaultMonth,
            selectedYear = defaultYear,
            expandedTripId = visibleTrips.firstOrNull()?.id,
        )
    }

    fun filterTrips(
        allTrips: List<Trip>,
        selectedApp: String,
        selectedScope: TripScope,
        selectedDay: String,
        selectedWeek: String,
        selectedMonth: String,
        selectedYear: String,
    ): List<Trip> {
        val scoped = when (selectedScope) {
            TripScope.Day -> {
                val ids = when (selectedDay) {
                    "Mar 6, 2026" -> setOf(1L, 2L, 3L, 4L)
                    "Mar 5, 2026" -> setOf(5L, 6L)
                    "Mar 4, 2026" -> setOf(7L)
                    else -> setOf(8L)
                }
                allTrips.filter { it.id in ids }
            }
            TripScope.Week -> {
                val ids = when (selectedWeek) {
                    "Mar 2 – Mar 8" -> setOf(1L, 2L, 3L, 4L, 5L, 6L)
                    "Feb 24 – Mar 1" -> setOf(5L, 6L, 7L)
                    else -> setOf(8L)
                }
                allTrips.filter { it.id in ids }
            }
            TripScope.Month -> {
                val ids = when (selectedMonth) {
                    "February 2026" -> setOf(7L, 8L)
                    "March 2026" -> allTrips.map { it.id }.toSet()
                    else -> emptySet()
                }
                allTrips.filter { it.id in ids }
            }
            TripScope.Year -> {
                if (selectedYear == "2026") allTrips else emptyList()
            }
        }

        return if (selectedApp == "All apps") {
            scoped
        } else {
            scoped.filter { platformLabel(it.platform) == selectedApp }
        }
    }

    companion object {
        fun platformLabel(platform: GigPlatform?): String = when (platform) {
            GigPlatform.UBER -> "Uber"
            GigPlatform.DOORDASH -> "DoorDash"
            GigPlatform.LYFT -> "Lyft"
            GigPlatform.AMAZON_FLEX -> "Amazon Flex"
            GigPlatform.SPARK_DRIVER -> "Spark Driver"
            GigPlatform.INSTACART -> "Instacart"
            GigPlatform.GRUBHUB -> "Grubhub"
            null -> "Unknown"
        }
    }
}
