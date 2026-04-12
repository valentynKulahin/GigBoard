package com.gigboard.feature.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gigboard.core.maps.InteractiveMap
import com.gigboard.core.maps.MapDefaults
import com.gigboard.core.model.GigPlatform
import com.gigboard.core.model.Trip
import com.gigboard.core.model.TripType
import com.gigboard.feature.map.component.MapDateHeader
import com.gigboard.feature.map.component.MapDaySummaryBar
import com.google.maps.android.compose.Polyline
import com.google.android.gms.maps.model.LatLng as GmsLatLng

@Composable
fun MapRoute(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is MapUiState.Loading -> MapLoading()
        is MapUiState.Success -> MapContent(
            state = state,
            onEvent = viewModel::onEvent,
        )
        is MapUiState.Error -> MapError(message = state.message)
    }
}

@Composable
private fun MapLoading() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Loading...", color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun MapError(message: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(message, color = MaterialTheme.colorScheme.error)
    }
}

@Composable
private fun MapContent(
    state: MapUiState.Success,
    onEvent: (MapUiEvent) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Fullscreen interactive map
        InteractiveMap(
            modifier = Modifier.fillMaxSize(),
        ) {
            state.trips.forEach { trip ->
                TripPolyline(trip = trip)
            }
        }

        // Date header overlay — top
        MapDateHeader(
            currentDate = state.currentDate,
            onPrevious = { onEvent(MapUiEvent.NavigatePrevious) },
            onNext = { onEvent(MapUiEvent.NavigateNext) },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .systemBarsPadding()
                .padding(horizontal = 16.dp, vertical = 8.dp),
        )

        // Summary bar overlay — bottom
        MapDaySummaryBar(
            totalMiles = state.totalMiles,
            offTripMiles = state.offTripMiles,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 16.dp),
        )
    }
}

@Composable
private fun TripPolyline(trip: Trip) {
    val points = remember(trip.routePoints) {
        trip.routePoints.map { GmsLatLng(it.latitude, it.longitude) }
    }
    if (points.size < 2) return

    val color = if (trip.tripType == TripType.OFF_TRIP) {
        Color(0xFF9E9E9E) // grey for off-trip / dead miles
    } else {
        platformColor(trip.platform)
    }

    Polyline(
        points = points,
        color = color,
        width = if (trip.tripType == TripType.OFF_TRIP) 5f else MapDefaults.ROUTE_WIDTH,
    )
}

private fun platformColor(platform: GigPlatform): Color = when (platform) {
    GigPlatform.UBER -> Color(0xFF000000)
    GigPlatform.LYFT -> Color(0xFFFF00BF)
    GigPlatform.DOORDASH -> Color(0xFFFF3008)
    GigPlatform.GRUBHUB -> Color(0xFFF63440)
    GigPlatform.INSTACART -> Color(0xFF43B02A)
    GigPlatform.AMAZON_FLEX -> Color(0xFFFF9900)
    GigPlatform.SPARK_DRIVER -> Color(0xFF0071DC)
}