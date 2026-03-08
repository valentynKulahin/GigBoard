package com.gigboard.core.domain.repository

import com.gigboard.core.model.DailySummary
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {
    fun getDailySummary(): Flow<DailySummary>
}
