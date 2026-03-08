package com.gigboard.feature.home.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gigboard.core.model.PlatformBreakdown
import com.gigboard.feature.home.miles.util.uiColor

@Composable
fun EarningsDonutCard(
    breakdown: List<PlatformBreakdown>,
    totalEarnings: Double,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
) {
    CardContainer {
        Text(
            "EARNINGS BY PLATFORM",
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.sp,
            ),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DonutChart(
                breakdown = breakdown,
                totalEarnings = totalEarnings,
                selectedIndex = selectedIndex,
                onSelect = onSelect,
                size = 160.dp,
            )
            Spacer(Modifier.width(14.dp))
            DonutLegend(
                breakdown = breakdown,
                selectedIndex = selectedIndex,
                onSelect = onSelect,
            )
        }
    }
}

@Composable
private fun DonutChart(
    breakdown: List<PlatformBreakdown>,
    totalEarnings: Double,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    size: Dp,
) {
    val hasSelection = selectedIndex >= 0
    val selected = if (hasSelection) breakdown.getOrNull(selectedIndex) else null
    val isDark = isSystemInDarkTheme()

    Box(contentAlignment = Alignment.Center) {
        val sizePx = with(LocalDensity.current) { size.toPx() }

        Canvas(
            modifier = Modifier
                .size(size)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                ) { onSelect(-1) },
        ) {
            val strokeWidth = sizePx * 0.20f
            val radius = (sizePx - strokeWidth) / 2f
            val topLeft = Offset(strokeWidth / 2f, strokeWidth / 2f)
            val arcSize = Size(radius * 2f, radius * 2f)
            val gapDegrees = 2f

            var startAngle = -90f

            breakdown.forEachIndexed { index, item ->
                val sweep = (item.earnings / totalEarnings * 360f).toFloat() - gapDegrees
                val isSelected = selectedIndex == index
                val dimmed = hasSelection && !isSelected

                val alpha = if (dimmed) 0.15f else 1f
                val width = if (isSelected) strokeWidth + 8f else strokeWidth

                drawArc(
                    color = item.platform.uiColor(isDark).copy(alpha = alpha),
                    startAngle = startAngle,
                    sweepAngle = sweep,
                    useCenter = false,
                    topLeft = topLeft,
                    size = arcSize,
                    style = Stroke(width = width, cap = StrokeCap.Butt),
                )
                startAngle += sweep + gapDegrees
            }
        }

        // Center text
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (selected != null) {
                Text(
                    "$${selected.earnings.toInt()}",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    "${"%.1f".format(selected.miles)} mi",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    selected.platform.displayName,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 9.sp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            } else {
                Text(
                    "$${totalEarnings.toInt()}",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    "today",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun DonutLegend(
    breakdown: List<PlatformBreakdown>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
) {
    val hasSelection = selectedIndex >= 0

    Column {
        breakdown.forEachIndexed { index, item ->
            val alpha by animateFloatAsState(
                targetValue = if (hasSelection && selectedIndex != index) 0.25f else 1f,
                animationSpec = tween(250),
                label = "legend_$index",
            )
            Row(
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                    ) { onSelect(if (selectedIndex == index) -1 else index) }
                    .padding(vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(item.platform.uiColor(isSystemInDarkTheme()).copy(alpha = alpha)),
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    item.platform.displayName,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.5.sp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = alpha),
                )
            }
        }
    }
}
