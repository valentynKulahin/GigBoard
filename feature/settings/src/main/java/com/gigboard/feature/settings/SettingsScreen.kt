package com.gigboard.feature.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gigboard.core.designsystem.theme.ThemeMode

@Composable
fun SettingsScreen(
    currentThemeMode: ThemeMode,
    onThemeChanged: (ThemeMode) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            "GIGBOARD",
            style = MaterialTheme.typography.bodySmall.copy(letterSpacing = 3.sp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            "Settings",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(4.dp))

        // Profile
        Surface(
            shape = RoundedCornerShape(14.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable { }
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "J",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Column(Modifier.weight(1f)) {
                    Text("John D.", style = MaterialTheme.typography.titleLarge)
                    Text(
                        "Houston, TX · Since Jan 2025",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Text(
                    "›",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                )
            }
        }

        // Appearance
        Surface(
            shape = RoundedCornerShape(14.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            Column(Modifier.padding(14.dp)) {
                Text("Appearance", style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    ThemeMode.entries.forEach { mode ->
                        val isActive = currentThemeMode == mode
                        val label = when (mode) {
                            ThemeMode.LIGHT -> "Light"; ThemeMode.DARK -> "Dark"; ThemeMode.SYSTEM -> "System"
                        }
                        val icon = when (mode) {
                            ThemeMode.LIGHT -> "☀"; ThemeMode.DARK -> "☾"; ThemeMode.SYSTEM -> "⚙"
                        }
                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { onThemeChanged(mode) },
                            shape = RoundedCornerShape(10.dp),
                            color = if (isActive) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.outline.copy(
                                alpha = 0.1f
                            ),
                            border = if (isActive) BorderStroke(
                                1.5.dp,
                                MaterialTheme.colorScheme.primary
                            ) else null,
                        ) {
                            Column(
                                Modifier.padding(vertical = 10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(icon, fontSize = 16.sp)
                                Text(
                                    label,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal,
                                    color = if (isActive) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }

        // Menu
        SettingsMenuCard(
            listOf(
                "Connected Platforms" to "5 synced · 2 available",
                "Active Device" to "This device",
                "Notifications" to null,
                "Home Zone" to "Auto-detected",
                "Privacy" to null,
                "Tax Report" to null,
            )
        )

        SettingsMenuCard(listOf("Help & Support" to null, "Rate GigBoard" to null, "About" to null))

        Text(
            "GigBoard v0.1.0 MVP",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SettingsMenuCard(items: List<Pair<String, String?>>) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(Modifier.padding(14.dp)) {
            items.forEachIndexed { index, (label, subtitle) ->
                if (index > 0) HorizontalDivider(
                    color = MaterialTheme.colorScheme.outline.copy(
                        alpha = 0.5f
                    ), thickness = 0.5.dp
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable { }
                        .padding(vertical = 11.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(label, style = MaterialTheme.typography.bodyMedium)
                        if (subtitle != null) Text(
                            subtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Text("›", color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f))
                }
            }
        }
    }
}
