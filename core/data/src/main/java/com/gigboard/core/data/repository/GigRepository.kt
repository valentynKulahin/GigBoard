package com.gigboard.core.data.repository

import com.gigboard.core.model.DaySummary
import com.gigboard.core.model.GigPlatform
import com.gigboard.core.model.LatLng
import com.gigboard.core.model.Period
import com.gigboard.core.model.PlatformStats
import com.gigboard.core.model.Trip
import com.gigboard.core.model.TripType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GigRepository @Inject constructor() {

    fun getPlatformStats(period: Period): List<PlatformStats> {
        val multiplier = when (period) {
            Period.DAY -> 1.0
            Period.WEEK -> 5.2
            Period.MONTH -> 22.0
        }
        return listOf(
            PlatformStats(GigPlatform.UBER, 87.4 * multiplier, 214.50 * multiplier, (12 * multiplier).toInt(), 5.2 * multiplier),
            PlatformStats(GigPlatform.DOORDASH, 52.1 * multiplier, 127.80 * multiplier, (15 * multiplier).toInt(), 3.8 * multiplier),
            PlatformStats(GigPlatform.LYFT, 44.2 * multiplier, 98.30 * multiplier, (8 * multiplier).toInt(), 2.9 * multiplier),
            PlatformStats(GigPlatform.AMAZON_FLEX, 38.6 * multiplier, 89.40 * multiplier, (4 * multiplier).toInt(), 3.0 * multiplier),
            PlatformStats(GigPlatform.SPARK_DRIVER, 29.3 * multiplier, 72.10 * multiplier, (5 * multiplier).toInt(), 2.1 * multiplier),
            PlatformStats(GigPlatform.INSTACART, 21.7 * multiplier, 52.40 * multiplier, (6 * multiplier).toInt(), 1.8 * multiplier),
            PlatformStats(GigPlatform.GRUBHUB, 15.3 * multiplier, 38.90 * multiplier, (5 * multiplier).toInt(), 1.5 * multiplier),
        )
    }

    fun getWeekEarnings(): List<DaySummary> = listOf(
        DaySummary("Mo", 185.0, emptyList()),
        DaySummary("Tu", 220.0, emptyList()),
        DaySummary("We", 145.0, emptyList()),
        DaySummary("Th", 255.0, emptyList()),
        DaySummary("Fr", 290.0, emptyList()),
        DaySummary("Sa", 310.0, emptyList()),
        DaySummary("Su", 0.0, emptyList()),
    )

    fun getTrips(): List<Trip> = listOf(
        Trip("1", GigPlatform.UBER, TripType.VERIFIED, "Midtown area", "Downtown area", "8:12 AM", "8:41 AM", 7.2, 18.50,
            listOf(LatLng(29.755, -95.36), LatLng(29.75, -95.355), LatLng(29.74, -95.34))),
        Trip("2", null, TripType.OFF_TRIP, "Downtown area", "East side", "8:41 AM", "8:58 AM", 3.1, 0.0,
            listOf(LatLng(29.74, -95.34), LatLng(29.737, -95.335), LatLng(29.735, -95.33))),
        Trip("3", GigPlatform.DOORDASH, TripType.VERIFIED, "Restaurant district", "Residential area", "8:58 AM", "9:14 AM", 4.3, 8.75,
            listOf(LatLng(29.735, -95.33), LatLng(29.733, -95.335), LatLng(29.73, -95.34))),
        Trip("4", GigPlatform.DOORDASH, TripType.VERIFIED, "Restaurant district", "West side", "9:18 AM", "9:35 AM", 5.1, 11.20,
            listOf(LatLng(29.733, -95.34), LatLng(29.731, -95.345), LatLng(29.73, -95.35))),
        Trip("5", null, TripType.OFF_TRIP, "West side", "Warehouse district", "9:35 AM", "9:52 AM", 6.8, 0.0,
            listOf(LatLng(29.73, -95.35), LatLng(29.725, -95.365), LatLng(29.72, -95.38))),
        Trip("6", GigPlatform.AMAZON_FLEX, TripType.VERIFIED, "Warehouse district", "Delivery zone (12 stops)", "10:10 AM", "1:15 PM", 38.6, 89.40,
            listOf(LatLng(29.72, -95.38), LatLng(29.71, -95.36), LatLng(29.70, -95.34), LatLng(29.69, -95.37), LatLng(29.705, -95.39))),
        Trip("7", null, TripType.OFF_TRIP, "South area", "Home zone", "1:15 PM", "1:38 PM", 8.4, 0.0,
            listOf(LatLng(29.705, -95.39), LatLng(29.73, -95.38), LatLng(29.76, -95.37))),
        Trip("8", GigPlatform.UBER, TripType.VERIFIED, "Airport area", "Downtown area", "3:35 PM", "4:12 PM", 18.3, 42.80,
            listOf(LatLng(29.78, -95.34), LatLng(29.77, -95.345), LatLng(29.75, -95.36))),
        Trip("9", GigPlatform.LYFT, TripType.VERIFIED, "Downtown area", "Suburb heights", "4:18 PM", "4:52 PM", 14.7, 32.60,
            listOf(LatLng(29.75, -95.36), LatLng(29.745, -95.375), LatLng(29.74, -95.39))),
    )
}