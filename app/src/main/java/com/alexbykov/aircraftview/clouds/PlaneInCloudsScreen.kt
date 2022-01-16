package com.alexbykov.aircraftview.clouds

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alexbykov.aircraftview.R
import com.alexbykov.aircraftview.ui.theme.BlueLight
import offsetCloudAnimationAsState
import offsetPlaneAnimationAsState

@Composable
fun AircraftInCloudsScreen(viewModel: PlaneInCloudsViewModel) {

    val screenState by viewModel.observeState()
    val configuration = LocalConfiguration.current

    val fast: Dp by offsetCloudAnimationAsState(
        cloudState = screenState.animationState,
        screenWidth = configuration.screenWidthDp.dp,
        durationInMillis = 3_500
    )

    val veryFast: Dp by offsetCloudAnimationAsState(
        cloudState = screenState.animationState,
        screenWidth = configuration.screenWidthDp.dp,
        durationInMillis = 4_000
    )

    val slow: Dp by offsetCloudAnimationAsState(
        cloudState = screenState.animationState,
        screenWidth = configuration.screenWidthDp.dp,
        durationInMillis = 4_500
    )

    val verySlow: Dp by offsetCloudAnimationAsState(
        cloudState = screenState.animationState,
        screenWidth = configuration.screenWidthDp.dp,
        durationInMillis = 7_000
    )

    val normal: Dp by offsetCloudAnimationAsState(
        cloudState = screenState.animationState,
        screenWidth = configuration.screenWidthDp.dp,
        durationInMillis = 3_700
    )

    val planeMoveForwardAnimation: Dp by offsetPlaneAnimationAsState(
        cloudState = screenState.animationState,
        screenWidth = configuration.screenWidthDp.dp,
        durationInMillis = 5_000
    )

    Surface(color = Color.White) {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = BlueLight)
                    .padding(all = 16.dp)
            ) {

                screenState.clouds.forEachIndexed { index, cloud ->
                    val animation = when (cloud.cloudAnimation) {
                        PlaneInCloudsContract.CloudAnimation.VERY_SLOW -> verySlow
                        PlaneInCloudsContract.CloudAnimation.SLOW -> slow
                        PlaneInCloudsContract.CloudAnimation.FAST -> fast
                        PlaneInCloudsContract.CloudAnimation.VERY_FAST -> veryFast
                        PlaneInCloudsContract.CloudAnimation.NORMAL -> normal
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
        }
        Image(
            painter = painterResource(R.drawable.airplane),
            contentDescription = "Aircraft",
            modifier = Modifier
                .size(64.dp)
                .absoluteOffset(x = planeMoveForwardAnimation)
                .offset(
                    y = 50.dp
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