package com.gigboard.feature.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gigboard.core.designsystem.theme.ThemeMode
import com.gigboard.core.model.GigPlatform
import com.gigboard.feature.settings.component.AppearanceCard
import com.gigboard.feature.settings.component.MenuItem
import com.gigboard.feature.settings.component.ProfileCard
import com.gigboard.feature.settings.component.SettingsMenuCard

@Composable
fun SettingsRoute(
    onThemeChanged: (ThemeMode) -> Unit,
    currentThemeMode: ThemeMode,
    onConnectedPlatformsClick: () -> Unit,
    onAdvancedClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SettingsScreen(
        connectedCount = uiState.connectedPlatforms.size,
        currentThemeMode = currentThemeMode,
        onThemeChanged = onThemeChanged,
        onConnectedPlatformsClick = onConnectedPlatformsClick,
        onAdvancedClick = onAdvancedClick,
        modifier = modifier,
    )
}

@Composable
internal fun SettingsScreen(
    connectedCount: Int,
    currentThemeMode: ThemeMode,
    onThemeChanged: (ThemeMode) -> Unit,
    onConnectedPlatformsClick: () -> Unit,
    onAdvancedClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            "GIGBOARD",
            style = MaterialTheme.typography.bodySmall.copy(letterSpacing = 3.sp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            "Settings",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(Modifier.height(4.dp))

        ProfileCard(
            initial = "J",
            name = "John D.",
            subtitle = "Houston, TX · Since Jan 2025",
            onClick = { },
        )

        AppearanceCard(
            currentThemeMode = currentThemeMode,
            onThemeChanged = onThemeChanged,
        )

        SettingsMenuCard(
            items = listOf(
                MenuItem(
                    label = "Connected Platforms",
                    subtitle = "$connectedCount synced · ${GigPlatform.entries.size - connectedCount} available",
                    onClick = onConnectedPlatformsClick,
                ),
                MenuItem("Active Device", "This device"),
                MenuItem("Notifications"),
                MenuItem("Home Zone", "Auto-detected"),
                MenuItem("Privacy"),
                MenuItem("Tax Report"),
                MenuItem(
                    label = "Advanced",
                    subtitle = "Tracking, debug",
                    onClick = onAdvancedClick,
                ),
            )
        )

        SettingsMenuCard(
            items = listOf(
                MenuItem("Help & Support"),
                MenuItem("Rate GigBoard"),
                MenuItem("About"),
            )
        )

        Text(
            "GigBoard v0.1.0 MVP",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(80.dp))
    }
}