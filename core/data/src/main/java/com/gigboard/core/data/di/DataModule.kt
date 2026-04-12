package com.gigboard.core.data.di

import com.gigboard.core.data.repository.FakeDashboardRepositoryImpl
import com.gigboard.core.data.repository.FakeTripRepositoryImpl
import com.gigboard.core.domain.repository.DashboardRepository
import com.gigboard.core.domain.repository.TripRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindDashboardRepository(
        impl: FakeDashboardRepositoryImpl,
    ): DashboardRepository

    @Binds
    abstract fun bindTripsRepository(
        impl: FakeTripRepositoryImpl
    ): TripRepository

}
