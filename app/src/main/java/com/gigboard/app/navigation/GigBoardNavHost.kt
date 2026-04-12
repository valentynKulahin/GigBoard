package com.gigboard.app.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gigboard.core.designsystem.theme.ThemeMode
import com.gigboard.feature.home.HomeRoute
import com.gigboard.feature.map.MapRoute
import com.gigboard.feature.settings.SettingsRoute
import com.gigboard.feature.trips.TripsRoute
import com.gigboard.feature.settings.ConnectedPlatformsRoute
import com.gigboard.feature.settings.navigation.CONNECTED_PLATFORMS_ROUTE
import com.gigboard.feature.settings.navigation.ADVANCED_SETTINGS_ROUTE

@Composable
fun GigBoardNavHost(
    onThemeChanged: (ThemeMode) -> Unit,
    currentThemeMode: ThemeMode,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
                tonalElevation = 0.dp
            ) {
                TopLevelDestination.entries.forEach { destination ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == destination.route } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(destination.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(destination.icon, contentDescription = destination.label) },
                        label = {
                            Text(
                                destination.label,
                                style = MaterialTheme.typography.bodySmall
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                        ),
                    )
                }
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = TopLevelDestination.HOME.route,
            modifier = Modifier.padding(innerPadding),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
        ) {
            composable(TopLevelDestination.HOME.route) { HomeRoute() }
            composable(TopLevelDestination.TRIPS.route) { TripsRoute() }
            composable(TopLevelDestination.MAP.route) { MapRoute() }
            composable(TopLevelDestination.SETTINGS.route) {
                SettingsRoute(
                    onThemeChanged = onThemeChanged,
                    currentThemeMode = currentThemeMode,
                    onConnectedPlatformsClick = {
                        navController.navigate(CONNECTED_PLATFORMS_ROUTE)
                    },
                    onAdvancedClick = { navController.navigate(ADVANCED_SETTINGS_ROUTE) }
                )
            }
            composable(CONNECTED_PLATFORMS_ROUTE) {
                ConnectedPlatformsRoute(
                    onBack = { navController.popBackStack() },
                )
            }
            composable(ADVANCED_SETTINGS_ROUTE) {
                ConnectedPlatformsRoute(
                    onBack = { navController.popBackStack() },
                )
            }
        }
    }
}
