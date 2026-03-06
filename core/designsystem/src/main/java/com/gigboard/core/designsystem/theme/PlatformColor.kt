package com.gigboard.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.gigboard.core.model.GigPlatform

@Composable
fun GigPlatform.color(): Color {
    val isDark = isSystemInDarkTheme()
    return when (this) {
        GigPlatform.UBER -> if (isDark) UberDark else UberLight
        GigPlatform.DOORDASH -> if (isDark) DoorDashDark else DoorDashLight
        GigPlatform.LYFT -> if (isDark) LyftDark else LyftLight
        GigPlatform.AMAZON_FLEX -> if (isDark) AmazonFlexDark else AmazonFlexLight
        GigPlatform.SPARK_DRIVER -> if (isDark) SparkDriverDark else SparkDriverLight
        GigPlatform.INSTACART -> if (isDark) InstacartDark else InstacartLight
        GigPlatform.GRUBHUB -> if (isDark) GrubhubDark else GrubhubLight
    }
}
