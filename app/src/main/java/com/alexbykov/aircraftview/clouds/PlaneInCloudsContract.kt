package com.alexbykov.aircraftview.clouds

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alexbykov.aircraftview.ui.theme.GrayLight

interface PlaneInCloudsContract {

    companion object {
        fun createInitialState() =
            ScreenState(
                clouds = listOf(
                    Cloud(
                        color = GrayLight,
                        height = 20.dp,
                        width = 50.dp,
                        cloudAnimation = CloudAnimation.VERY_FAST,
                        verticalOffset = 4.dp
                    ),

                    Cloud(
                        color = Color.White,
                        height = 10.dp,
                        width = 40.dp,
                        cloudAnimation = CloudAnimation.VERY_SLOW,
                        verticalOffset = 8.dp
                    ),
                    Cloud(
                        color = GrayLight,
                        height = 20.dp,
                        width = 50.dp,
                        cloudAnimation = CloudAnimation.FAST,
                        verticalOffset = 20.dp
                    ),

                    Cloud(
                        color = Color.White,
                        height = 30.dp,
                        width = 60.dp,
                        cloudAnimation = CloudAnimation.SLOW,
                        verticalOffset = 8.dp
                    ),

                    Cloud(
                        color = GrayLight,
                        height = 40.dp,
                        width = 70.dp,
                        cloudAnimation = CloudAnimation.NORMAL,
                        verticalOffset = 8.dp
                    )
                ),
                animationState = AnimationState.Idle,
                controlButtonText = "Let's fly!"
            )
    }

    data class ScreenState(
        val clouds: List<Cloud>,
        val animationState: AnimationState = AnimationState.Idle,
        val controlButtonText: String
    )

    data class Cloud(
        val color: Color,
        val width: Dp,
        val height: Dp,
        val cloudAnimation: CloudAnimation,
        val verticalOffset: Dp = 0.dp
    )

    enum class CloudAnimation {
        VERY_FAST,
        FAST,
        NORMAL,
        SLOW,
        VERY_SLOW
    }


    sealed class AnimationState {
        object Idle : AnimationState()
        object Animating : AnimationState()
    }
}
