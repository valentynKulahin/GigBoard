package com.gigboard.core.data.di

import com.gigboard.core.data.repository.FakeDashboardRepository
import com.gigboard.core.data.repository.FakeTripsRepository
import com.gigboard.core.domain.repository.DashboardRepository
import com.gigboard.core.domain.repository.TripsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindDashboardRepository(
        impl: FakeDashboardRepository,
    ): DashboardRepository

    @Binds
    abstract fun bindTripsRepository(
        impl: FakeTripsRepository,
    ): TripsRepository

}
