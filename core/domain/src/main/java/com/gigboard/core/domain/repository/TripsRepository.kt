package com.gigboard.core.domain.repository

import com.gigboard.core.model.Trip
import kotlinx.coroutines.flow.Flow

interface TripsRepository {

    fun getTrips(): Flow<List<Trip>>

}