package com.alexbykov.aircraftview.clouds

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alexbykov.aircraftview.R
import offsetCloudAnimationDpAsState
import offsetPlaneAnimationDpAsState

@Composable
fun AircraftInCloudsScreen(viewModel: PlaneInCloudsViewModel) {

    val screenState by viewModel.observeState()
    val configuration = LocalConfiguration.current

    val fastCloudOffset: Dp by offsetCloudAnimationDpAsState(
        cloudState = screenState.animationState,
        screenWidth = configuration.screenWidthDp.dp,
        durationInMillis = 3_500
    )

    val veryFastCloudOffset: Dp by offsetCloudAnimationDpAsState(
        cloudState = screenState.animationState,
        screenWidth = configuration.screenWidthDp.dp,
        durationInMillis = 4_000
    )

    val slowCloudOffset: Dp by offsetCloudAnimationDpAsState(
        cloudState = screenState.animationState,
        screenWidth = configuration.screenWidthDp.dp,
        durationInMillis = 4_500
    )

    val verySlowCloudOffset: Dp by offsetCloudAnimationDpAsState(
        cloudState = screenState.animationState,
        screenWidth = configuration.screenWidthDp.dp,
        durationInMillis = 7_000
    )

    val normalCloudOffset: Dp by offsetCloudAnimationDpAsState(
        cloudState = screenState.animationState,
        screenWidth = configuration.screenWidthDp.dp,
        durationInMillis = 3_700
    )
    val planeMoveForwardOffset: Dp by offsetPlaneAnimationDpAsState(
        cloudState = screenState.animationState,
        screenWidth = configuration.screenWidthDp.dp,
        durationInMillis = 5_000
    )


    val planeY: Dp by animateDpAsState(
        targetValue = screenState.planeY.dp,
        animationSpec = tween(durationMillis = 300)
    )



    Surface(color = Color.White) {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = screenState.theme.colours
                        )
                    )
                    .padding(all = 16.dp)
            ) {

                screenState.clouds.forEachIndexed { index, cloud ->
                    val animation = when (cloud.cloudAnimation) {
                        PlaneInCloudsContract.CloudAnimation.VERY_SLOW -> verySlowCloudOffset
                        PlaneInCloudsContract.CloudAnimation.SLOW -> slowCloudOffset
                        PlaneInCloudsContract.CloudAnimation.FAST -> fastCloudOffset
                        PlaneInCloudsContract.CloudAnimation.VERY_FAST -> veryFastCloudOffset
                        PlaneInCloudsContract.CloudAnimation.NORMAL -> normalCloudOffset
                    }

                    val offsetVertical = cloud.verticalOffset

                    CloudView(
                        modifier = Modifier
                            .height(cloud.height)
                            .absoluteOffset(x = animation, y = offsetVertical)
                            .width(cloud.width),
                        color = cloud.color
                    )
                }
            }

            ControlButton(text = screenState.controlButtonText) {
                viewModel.onClickControlButton()
            }
            ControlButton(text = screenState.themeChangeText) {
                viewModel.onClickChangeTheme()
            }

            ControlButton(text = "Turbulence") {
                viewModel.onClickTurbulence()
            }
        }
        Image(
            painter = painterResource(R.drawable.airplane),
            contentDescription = "Aircraft",
            modifier = Modifier
                .size(64.dp)
                .absoluteOffset(x = planeMoveForwardOffset)
                .offset(
                    y = planeY
                )
        )
    }
}

@Composable
private fun ControlButton(
    text: String,
    onClick: () -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text)
    }
}