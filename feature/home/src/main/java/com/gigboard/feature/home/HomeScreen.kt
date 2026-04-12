package com.gigboard.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gigboard.core.model.DailySummary
import com.gigboard.feature.home.component.EarningsDonutCard
import com.gigboard.feature.home.component.HomeHeader
import com.gigboard.feature.home.component.PlatformBreakdownCard
import com.gigboard.feature.home.component.StatsRow

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    HomeScreen(viewModel = viewModel)
}

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is HomeUiState.Loading -> HomeLoading()
        is HomeUiState.Success -> HomeContent(summary = state.summary)
        is HomeUiState.Error -> HomeError(message = state.message)
    }
}

@Composable
private fun HomeLoading() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Loading...", color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun HomeError(message: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(message, color = MaterialTheme.colorScheme.error)
    }
}

@Composable
private fun HomeContent(summary: DailySummary) {
    var selectedPlatformIndex by remember { mutableIntStateOf(-1) }
    var expandedRowIndex by remember { mutableIntStateOf(-1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            ) { selectedPlatformIndex = -1 }
            .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        HomeHeader(totalMiles = summary.totalMilesToday)

        Spacer(Modifier.height(14.dp))

        EarningsDonutCard(
            breakdown = summary.platformBreakdown,
            totalEarnings = summary.totalEarningsToday,
            selectedIndex = selectedPlatformIndex,
            onSelect = { selectedPlatformIndex = it },
        )

        Spacer(Modifier.height(10.dp))

        StatsRow(
            totalTrips = summary.platformBreakdown.sumOf { it.trips },
            totalEarnings = summary.totalEarningsToday,
            totalMiles = summary.totalMilesToday
        )

        Spacer(Modifier.height(10.dp))

        PlatformBreakdownCard(
            breakdown = summary.platformBreakdown,
            deadMiles = summary.deadMiles,
            expandedIndex = expandedRowIndex,
            onToggle = { expandedRowIndex = if (expandedRowIndex == it) -1 else it },
        )

        Spacer(Modifier.height(80.dp))
    }
}
