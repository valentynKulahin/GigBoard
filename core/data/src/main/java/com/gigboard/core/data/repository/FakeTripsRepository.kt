package com.gigboard.core.data.repository

import com.gigboard.core.domain.repository.TripsRepository
import com.gigboard.core.model.GigPlatform
import com.gigboard.core.model.LatLng
import com.gigboard.core.model.Trip
import com.gigboard.core.model.TripType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import javax.inject.Inject

class FakeTripsRepository @Inject constructor() : TripsRepository {

    val today = LocalDate.now()
    val zone = ZoneId.systemDefault()

    override fun getTrips(): Flow<List<Trip>> = flowOf(
        listOf(

            Trip(
                id = 1L,
                platform = GigPlatform.UBER,
                type = TripType.VERIFIED,
                fromArea = "Downtown",
                toArea = "Pearl District",
                startTime = LocalTime.of(11, 42).atDate(today).atZone(zone).toInstant()
                    .toEpochMilli(),
                endTime = LocalTime.of(12, 6).atDate(today).atZone(zone).toInstant().toEpochMilli(),
                miles = 7.2,
                earnings = 18.45,
                routePoints = listOf(
                    LatLng(45.5230, -122.6765),
                    LatLng(45.5255, -122.6710),
                    LatLng(45.5290, -122.6660),
                    LatLng(45.5340, -122.6610),
                ),
            ),

            Trip(
                id = 2L,
                platform = GigPlatform.DOORDASH,
                type = TripType.VERIFIED,
                fromArea = "Hazel Dell",
                toArea = "Salmon Creek",
                startTime = LocalTime.of(12, 18).atDate(today).atZone(zone).toInstant()
                    .toEpochMilli(),
                endTime = LocalTime.of(12, 49).atDate(today).atZone(zone).toInstant()
                    .toEpochMilli(),
                miles = 5.4,
                earnings = 13.20,
                routePoints = listOf(
                    LatLng(45.6890, -122.6660),
                    LatLng(45.6940, -122.6590),
                    LatLng(45.7000, -122.6515),
                    LatLng(45.7060, -122.6450),
                ),
            ),

            Trip(
                id = 3L,
                platform = GigPlatform.LYFT,
                type = TripType.VERIFIED,
                fromArea = "Uptown",
                toArea = "Waterfront",
                startTime = LocalTime.of(14, 5).atDate(today).atZone(zone).toInstant()
                    .toEpochMilli(),
                endTime = LocalTime.of(14, 22).atDate(today).atZone(zone).toInstant()
                    .toEpochMilli(),
                miles = 4.1,
                earnings = 11.80,
                routePoints = listOf(
                    LatLng(45.5310, -122.6900),
                    LatLng(45.5270, -122.6840),
                    LatLng(45.5235, -122.6780),
                    LatLng(45.5200, -122.6700),
                ),
            ),

            Trip(
                id = 4L,
                platform = GigPlatform.AMAZON_FLEX,
                type = TripType.VERIFIED,
                fromArea = "VOR3",
                toArea = "Battle Ground loop",
                startTime = LocalTime.of(17, 42).atDate(today).atZone(zone).toInstant()
                    .toEpochMilli(),
                endTime = LocalTime.of(19, 24).atDate(today).atZone(zone).toInstant()
                    .toEpochMilli(),
                miles = 28.2,
                earnings = 54.00,
                routePoints = listOf(
                    LatLng(45.6400, -122.6000),
                    LatLng(45.6900, -122.5700),
                    LatLng(45.7300, -122.5400),
                    LatLng(45.7700, -122.5000),
                    LatLng(45.8100, -122.4700),
                ),
            ),

            Trip(
                id = 5L,
                platform = GigPlatform.SPARK_DRIVER,
                type = TripType.VERIFIED,
                fromArea = "Walmart East",
                toArea = "Orchards",
                startTime = LocalTime.of(9, 14).atDate(today).atZone(zone).toInstant()
                    .toEpochMilli(),
                endTime = LocalTime.of(10, 0).atDate(today).atZone(zone).toInstant().toEpochMilli(),
                miles = 10.8,
                earnings = 21.75,
                routePoints = listOf(
                    LatLng(45.6580, -122.5600),
                    LatLng(45.6620, -122.5480),
                    LatLng(45.6670, -122.5360),
                    LatLng(45.6720, -122.5200),
                ),
            ),

            Trip(
                id = 6L,
                platform = GigPlatform.UBER,
                type = TripType.VERIFIED,
                fromArea = "Airport",
                toArea = "Vancouver Mall",
                startTime = LocalTime.of(21, 38).atDate(today).atZone(zone).toInstant()
                    .toEpochMilli(),
                endTime = LocalTime.of(22, 6).atDate(today).atZone(zone).toInstant().toEpochMilli(),
                miles = 6.7,
                earnings = 16.40,
                routePoints = listOf(
                    LatLng(45.5887, -122.5951),
                    LatLng(45.6100, -122.6000),
                    LatLng(45.6350, -122.6120),
                    LatLng(45.6520, -122.5910),
                ),
            ),

            Trip(
                id = 7L,
                platform = GigPlatform.DOORDASH,
                type = TripType.VERIFIED,
                fromArea = "Camas",
                toArea = "Fishers Landing",
                startTime = LocalTime.of(19, 11).atDate(today).atZone(zone).toInstant()
                    .toEpochMilli(),
                endTime = LocalTime.of(19, 46).atDate(today).atZone(zone).toInstant()
                    .toEpochMilli(),
                miles = 5.9,
                earnings = 14.90,
                routePoints = listOf(
                    LatLng(45.5850, -122.3990),
                    LatLng(45.5710, -122.4500),
                    LatLng(45.5620, -122.5000),
                    LatLng(45.5550, -122.5300),
                ),
            ),

            Trip(
                id = 8L,
                platform = GigPlatform.INSTACART,
                type = TripType.VERIFIED,
                fromArea = "Costco",
                toArea = "Felida",
                startTime = LocalTime.of(15, 26).atDate(today).atZone(zone).toInstant()
                    .toEpochMilli(),
                endTime = LocalTime.of(16, 24).atDate(today).atZone(zone).toInstant()
                    .toEpochMilli(),
                miles = 12.6,
                earnings = 24.35,
                routePoints = listOf(
                    LatLng(45.6210, -122.6710),
                    LatLng(45.6500, -122.6800),
                    LatLng(45.6900, -122.7000),
                    LatLng(45.7250, -122.7200),
                ),
            )
        )
    )

}