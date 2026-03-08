package com.gigboard.feature.trips

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gigboard.core.model.Trip
import com.gigboard.feature.trips.component.TripHistoryRow
import com.gigboard.feature.trips.component.TripsFilterPanel
import com.gigboard.feature.trips.component.TripsHeader
import com.gigboard.feature.trips.component.TripsToolbar

@Composable
fun TripsScreen(
    viewModel: TripsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()

    TripsContent(
        uiState = state,
        onAction = viewModel::onAction,
    )
}

@Composable
internal fun TripsContent(
    uiState: TripsUiState,
    onAction: (TripsAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        TripsHeader(count = uiState.visibleTrips.size)

        TripsToolbar(
            apps = uiState.appFilters,
            selectedApp = uiState.selectedApp,
            filtersExpanded = uiState.filtersExpanded,
            onAppSelected = { onAction(TripsAction.SelectApp(it)) },
            onToggleFilters = { onAction(TripsAction.ToggleFilters) },
            modifier = Modifier.padding(horizontal = 16.dp),
        )

        AnimatedVisibility(visible = uiState.filtersExpanded) {
            TripsFilterPanel(
                selectedScope = uiState.selectedScope,
                currentScopeValue = uiState.currentScopeValue,
                onScopeSelected = { onAction(TripsAction.SelectScope(it)) },
                onPreviousValue = { onAction(TripsAction.StepScopeValuePrevious) },
                onNextValue = { onAction(TripsAction.StepScopeValueNext) },
                onClearFilters = { onAction(TripsAction.ClearFilters) },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 6.dp),
            )
        }

        TripsHistoryCard(
            trips = uiState.visibleTrips,
            currentScopeValue = uiState.currentScopeValue,
            expandedTripId = uiState.expandedTripId,
            filtersExpanded = uiState.filtersExpanded,
            onTripToggle = { onAction(TripsAction.ToggleTripExpansion(it)) },
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun TripsHistoryCard(
    trips: List<Trip>,
    currentScopeValue: String,
    expandedTripId: Long?,
    filtersExpanded: Boolean,
    onTripToggle: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = if (filtersExpanded) 8.dp else 12.dp),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Trip history",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = currentScopeValue,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            if (trips.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "No trips for these filters",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = WindowInsets.navigationBars.asPaddingValues(),
                ) {
                    items(trips, key = { it.id }) { trip ->
                        TripHistoryRow(
                            trip = trip,
                            expanded = expandedTripId == trip.id,
                            onToggle = { onTripToggle(trip.id) },
                        )
                    }
                }
            }
        }
    }
}
