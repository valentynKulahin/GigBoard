package com.gigboard.core.model

data class DailySummary(
    val totalMilesToday: Double,
    val totalEarningsToday: Double,
    val deadMiles: Double,
    val platformBreakdown: List<PlatformBreakdown>,
)

data class PlatformBreakdown(
    val platform: GigPlatform,
    val earnings: Double,
    val miles: Double,
    val trips: Int,
    val hours: Double,
) {
    val avgPerTrip: Double get() = if (trips > 0) earnings / trips else 0.0
    val avgMilesPerTrip: Double get() = if (trips > 0) miles / trips else 0.0
    val avgPerHour: Double get() = if (hours > 0.0) earnings / hours else 0.0
}
