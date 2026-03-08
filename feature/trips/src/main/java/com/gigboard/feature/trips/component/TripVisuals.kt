package com.gigboard.feature.trips.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gigboard.core.model.GigPlatform
import com.gigboard.core.model.LatLng
import com.gigboard.feature.trips.TripsReducer

@Composable
internal fun PlatformBadge(platform: GigPlatform?) {
    val color = platformColor(platform)
    val label = TripsReducer.Companion.platformLabel(platform).firstOrNull()?.uppercase() ?: "?"

    Box(
        modifier = Modifier
            .size(34.dp)
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(10.dp))
            .border(1.5.dp, color.copy(alpha = 0.3f), RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = color,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
internal fun MiniRouteMap(route: List<LatLng>) {
    val stroke = MaterialTheme.colorScheme.primary
    val grid = MaterialTheme.colorScheme.outline.copy(alpha = 0.45f)
    val mapBg = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)

    val normalized = if (route.isEmpty()) {
        listOf(
            Offset(0.08f, 0.82f),
            Offset(0.25f, 0.68f),
            Offset(0.45f, 0.55f),
            Offset(0.72f, 0.42f),
            Offset(0.92f, 0.31f),
        )
    } else {
        val minLat = route.minOf { it.latitude }
        val maxLat = route.maxOf { it.latitude }
        val minLng = route.minOf { it.longitude }
        val maxLng = route.maxOf { it.longitude }
        val latRange = (maxLat - minLat).takeIf { it != 0.0 } ?: 1.0
        val lngRange = (maxLng - minLng).takeIf { it != 0.0 } ?: 1.0

        route.map {
            Offset(
                x = (((it.longitude - minLng) / lngRange) * 0.84 + 0.08).toFloat(),
                y = (0.92 - ((it.latitude - minLat) / latRange) * 0.74).toFloat(),
            )
        }
    }

    val color = MaterialTheme.colorScheme.onSurface

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(124.dp)
            .background(mapBg, RoundedCornerShape(12.dp)),
    ) {
        val width = size.width
        val height = size.height

        listOf(0.125f, 0.25f, 0.375f, 0.5f, 0.625f, 0.75f, 0.875f).forEach { fraction ->
            drawLine(
                color = grid,
                start = Offset(width * fraction, 0f),
                end = Offset(width * fraction, height),
            )
        }
        listOf(0.2f, 0.4f, 0.6f, 0.8f).forEach { fraction ->
            drawLine(
                color = grid,
                start = Offset(0f, height * fraction),
                end = Offset(width, height * fraction),
            )
        }

        val path = Path().apply {
            normalized.forEachIndexed { index, point ->
                val x = width * point.x
                val y = height * point.y
                if (index == 0) moveTo(x, y) else lineTo(x, y)
            }
        }

        drawPath(path = path, color = stroke, style = Stroke(width = 8f, cap = StrokeCap.Round))

        normalized.firstOrNull()?.let { start ->
            drawCircle(
                color = color,
                radius = 10f,
                center = Offset(width * start.x, height * start.y),
            )
        }
        normalized.lastOrNull()?.let { end ->
            drawCircle(
                color = color,
                radius = 10f,
                center = Offset(width * end.x, height * end.y),
            )
        }
    }
}

@Composable
private fun platformColor(platform: GigPlatform?): Color = when (platform) {
    GigPlatform.UBER -> Color(0xFFFFFFFF)
    GigPlatform.DOORDASH -> Color(0xFFFF5533)
    GigPlatform.LYFT -> Color(0xFFFF47D1)
    GigPlatform.AMAZON_FLEX -> Color(0xFFFFB340)
    GigPlatform.SPARK_DRIVER -> Color(0xFF4DA3FF)
    GigPlatform.INSTACART -> Color(0xFF5DD641)
    GigPlatform.GRUBHUB -> Color(0xFFFF6169)
    null -> MaterialTheme.colorScheme.primary
}
