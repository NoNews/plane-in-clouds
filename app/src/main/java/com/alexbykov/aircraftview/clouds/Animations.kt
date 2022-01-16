import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alexbykov.aircraftview.clouds.PlaneInCloudsContract


@Composable
fun offsetCloudAnimationDpAsState(
    cloudState: PlaneInCloudsContract.AnimationState,
    screenWidth: Dp,
    durationInMillis: Int
): State<Dp> {

    return animateDpAsState(
        targetValue = if (cloudState == PlaneInCloudsContract.AnimationState.Idle) {
            screenWidth + 64.dp
        } else {
            (-100).dp
        },
        animationSpec =
        if (cloudState == PlaneInCloudsContract.AnimationState.Idle) {
            tween()
        } else {
            infiniteRepeatable(
                animation = tween(
                    delayMillis = 0,
                    durationMillis = durationInMillis,
                    easing = CubicBezierEasing(0.2f, 0.0f, 0.7f, 0.7f)
                ),
                repeatMode = RepeatMode.Restart
            )
        }
    )
}


@Composable
fun offsetPlaneAnimationDpAsState(
    cloudState: PlaneInCloudsContract.AnimationState,
    screenWidth: Dp,
    durationInMillis: Int
): State<Dp> {

    return animateDpAsState(
        targetValue = if (cloudState == PlaneInCloudsContract.AnimationState.Idle) {
            (-100).dp
        } else {
            (screenWidth * 0.3F)
        },
        animationSpec =
        if (cloudState == PlaneInCloudsContract.AnimationState.Idle) {
            tween()
        } else {
            tween(
                delayMillis = 0,
                durationMillis = durationInMillis,
                easing = CubicBezierEasing(a = 0.2f, b = 0.0f, c = 0.7f, d = 0.7f)
            )
        }
    )
}

