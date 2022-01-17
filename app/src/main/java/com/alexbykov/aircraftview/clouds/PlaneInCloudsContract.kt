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
                controlButtonText = "Let's fly!",
                themeChangeText = "Night",
                theme = Theme.Day
            )
    }

    data class ScreenState(
        val clouds: List<Cloud>,
        val animationState: AnimationState = AnimationState.Idle,
        val controlButtonText: String,
        val themeChangeText: String,
        val theme: Theme
    )

    sealed class Theme {
        abstract val colours: List<Color>

        object Day : Theme() {
            override val colours: List<Color>
                get() = listOf(
                    Color(0xff29b6f6),
                    Color(0xff81d4fa),
                    Color(0xffb3e5fc),
                    Color(0xffe1f5fe),
                    Color(0xffe0f7fa),
                    Color.White
                )
        }

        object Night : Theme() {
            override val colours: List<Color>
                get() = listOf(
                    Color(0xFF424242),
                    Color(0xFF616161),
                    Color(0xff757575),
                    Color(0xffbdbdbd),
                )
        }
    }

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
