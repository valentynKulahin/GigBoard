package com.gigboard.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarViewDay
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopLevelDestination(
    val route: String,
    val label: String,
    val icon: ImageVector,
) {
    HOME("home", "Home", Icons.Outlined.Home),
    TRIPS("trips", "Trips", Icons.Outlined.CalendarViewDay),
    MAP("map", "Map", Icons.Outlined.Map),
    SETTINGS("settings", "Settings", Icons.Outlined.Settings),
}
