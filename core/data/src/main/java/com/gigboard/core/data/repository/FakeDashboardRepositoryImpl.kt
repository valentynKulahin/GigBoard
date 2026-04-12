package com.gigboard.core.data.repository

import com.gigboard.core.domain.repository.DashboardRepository
import com.gigboard.core.model.DailySummary
import com.gigboard.core.model.GigPlatform
import com.gigboard.core.model.PlatformBreakdown
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FakeDashboardRepositoryImpl @Inject constructor() : DashboardRepository {

    override fun getDailySummary(): Flow<DailySummary> = flowOf(
        DailySummary(
            totalMilesToday = 254.3,
            totalEarningsToday = 391.0,
            deadMiles = 24.6,
            platformBreakdown = listOf(
                PlatformBreakdown(
                    platform = GigPlatform.UBER,
                    earnings = 89.50,
                    miles = 42.3,
                    trips = 7,
                    hours = 3.2,
                ),
                PlatformBreakdown(
                    platform = GigPlatform.DOORDASH,
                    earnings = 64.20,
                    miles = 28.1,
                    trips = 5,
                    hours = 2.5,
                ),
                PlatformBreakdown(
                    platform = GigPlatform.LYFT,
                    earnings = 45.80,
                    miles = 31.5,
                    trips = 4,
                    hours = 1.8,
                ),
                PlatformBreakdown(
                    platform = GigPlatform.AMAZON_FLEX,
                    earnings = 78.00,
                    miles = 55.2,
                    trips = 2,
                    hours = 3.0,
                ),
                PlatformBreakdown(
                    platform = GigPlatform.SPARK_DRIVER,
                    earnings = 52.30,
                    miles = 38.6,
                    trips = 3,
                    hours = 2.1,
                ),
                PlatformBreakdown(
                    platform = GigPlatform.INSTACART,
                    earnings = 32.50,
                    miles = 15.8,
                    trips = 3,
                    hours = 1.4,
                ),
                PlatformBreakdown(
                    platform = GigPlatform.GRUBHUB,
                    earnings = 28.70,
                    miles = 18.2,
                    trips = 3,
                    hours = 1.2,
                ),
            ),
        )
    )
}
