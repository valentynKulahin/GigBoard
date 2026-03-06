package com.gigboard.core.model

data class PlatformStats(
    val platform: GigPlatform,
    val miles: Double,
    val earnings: Double,
    val trips: Int,
    val hours: Double,
) {
    val earningsPerHour: Double get() = if (hours > 0) earnings / hours else 0.0
    val earningsPerMile: Double get() = if (miles > 0) earnings / miles else 0.0
    val earningsPerTrip: Double get() = if (trips > 0) earnings / trips else 0.0
    val milesPerTrip: Double get() = if (trips > 0) miles / trips else 0.0
}
