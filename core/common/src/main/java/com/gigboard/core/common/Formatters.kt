package com.gigboard.core.common

import java.text.NumberFormat
import java.util.Locale

fun Double.formatCurrency(): String {
    return NumberFormat.getCurrencyInstance(Locale.US).format(this)
}

fun Double.formatMiles(): String {
    return if (this >= 100) "%.0f".format(this) else "%.1f".format(this)
}

fun Double.formatPerHour(): String {
    return "$${this.toInt()}/hr"
}
