package com.gigboard.core.model

data class DaySummary(
    val label: String,
    val earnings: Double,
    val platformStats: List<PlatformStats>,
)
