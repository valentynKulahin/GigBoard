package com.gigboard.core.domain

import com.gigboard.core.domain.repository.DashboardRepository
import com.gigboard.core.model.DailySummary
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDailySummaryUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository,
) {

    operator fun invoke(): Flow<DailySummary> =
        dashboardRepository.getDailySummary()

}
