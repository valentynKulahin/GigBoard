package com.gigboard.core.model

data class Trip(
    val id: Long,
    val platform: GigPlatform?,
    val type: TripType,
    val fromArea: String,
    val toArea: String,
    val startTime: Long,
    val endTime: Long,
    val miles: Double,
    val earnings: Double,
    val routePoints: List<LatLng> = emptyList(),
)

enum class TripType { VERIFIED, OFF_TRIP }

data class LatLng(
    val latitude: Double,
    val longitude: Double,
)
