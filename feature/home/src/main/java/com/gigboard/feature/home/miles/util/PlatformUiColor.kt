package com.gigboard.feature.home.miles.util

import androidx.compose.ui.graphics.Color
import com.gigboard.core.designsystem.theme.*
import com.gigboard.core.model.GigPlatform

fun GigPlatform.uiColor(isDark: Boolean): Color {
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
