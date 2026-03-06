package com.gigboard.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

enum class ThemeMode { LIGHT, DARK, SYSTEM }

private val DarkColorScheme = darkColorScheme(
    primary = DarkAccent, onPrimary = DarkBackground,
    background = DarkBackground, surface = DarkSurface,
    surfaceVariant = DarkCard, outline = DarkBorder,
    onBackground = DarkText, onSurface = DarkText,
    onSurfaceVariant = DarkTextSecondary,
    tertiary = GreenPositive, error = RedNegative,
)

private val LightColorScheme = lightColorScheme(
    primary = LightAccent, onPrimary = Color.White,
    background = LightBackground, surface = LightSurface,
    surfaceVariant = LightCard, outline = LightBorder,
    onBackground = LightText, onSurface = LightText,
    onSurfaceVariant = LightTextSecondary,
    tertiary = GreenPositiveLight, error = RedNegativeLight,
)

@Composable
fun GigBoardTheme(
    themeMode: ThemeMode = ThemeMode.SYSTEM,
    content: @Composable () -> Unit,
) {
    val isDark = when (themeMode) {
        ThemeMode.DARK -> true
        ThemeMode.LIGHT -> false
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
    }
    val colorScheme = if (isDark) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as android.app.Activity).window
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !isDark
                isAppearanceLightNavigationBars = !isDark
            }
        }
    }

    MaterialTheme(colorScheme = colorScheme, typography = GigBoardTypography, content = content)
}
