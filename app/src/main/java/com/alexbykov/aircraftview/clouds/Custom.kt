package com.alexbykov.aircraftview.clouds

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

@Composable
fun CloudView(
    color: Color = Color.Gray,
    modifier: Modifier,
) {
    Canvas(
        modifier = modifier,
    ) {
        val canvasHeight = size.height
        val canvasWidth = size.width

        val startOfCloud = Path()
        startOfCloud.moveTo(x = 0F, y = canvasHeight)

        startOfCloud.quadraticBezierTo(
            x1 = canvasWidth * 0.20F,
            y1 = 0f,
            x2 = canvasWidth * 0.45F,
            y2 = canvasHeight
        )


        val endOfCloud = Path()
        endOfCloud.moveTo(x = canvasWidth * 0.20F, y = canvasHeight)

        endOfCloud.quadraticBezierTo(
            x1 = canvasWidth * 0.55F,
            y1 = -canvasHeight + canvasHeight * 0.20F,
            x2 = canvasWidth,
            y2 = canvasHeight
        )

        drawPath(
            path = startOfCloud,
            color = color
        )
        drawPath(
            path = endOfCloud,
            color = color
        )
    }
}