package com.alexbykov.aircraftview.clouds

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.random.Random

class PlaneInCloudsViewModel : ViewModel() {

    private val state: MutableState<PlaneInCloudsContract.ScreenState> =
        mutableStateOf(PlaneInCloudsContract.createInitialState())


    private var shakingJob: Job? = null

    fun observeState(): State<PlaneInCloudsContract.ScreenState> = state

    fun onClickControlButton() {
        when (state.value.animationState) {
            PlaneInCloudsContract.AnimationState.Animating -> {
                state.reduce { oldState ->
                    oldState.copy(
                        animationState = PlaneInCloudsContract.AnimationState.Idle,
                        controlButtonText = "Let's fly"
                    )
                }
            }
            PlaneInCloudsContract.AnimationState.Idle -> {
                state.reduce { oldState ->
                    oldState.copy(
                        animationState = PlaneInCloudsContract.AnimationState.Animating,
                        controlButtonText = "Stop"
                    )
                }
            }
        }
    }


    fun onClickChangeTheme() {
        state.reduce { oldState ->

            val changeThemeButtonText: String
            val newTheme: PlaneInCloudsContract.Theme
            val clouds: List<PlaneInCloudsContract.Cloud>

            when (oldState.theme) {
                is PlaneInCloudsContract.Theme.Day -> {
                    changeThemeButtonText = "Day"
                    newTheme = PlaneInCloudsContract.Theme.London
                    clouds = PlaneInCloudsContract.londonClouds()
                }
                is PlaneInCloudsContract.Theme.London -> {
                    changeThemeButtonText = "London"
                    newTheme = PlaneInCloudsContract.Theme.Day
                    clouds = PlaneInCloudsContract.dayClouds()
                }
            }

            oldState.copy(
                theme = newTheme,
                themeChangeText = changeThemeButtonText,
                clouds = clouds
            )
        }
    }

    private inline fun <T> MutableState<T>.reduce(
        reducer: (currentState: T) -> T
    ) {
        val newState = reducer.invoke(value)
        value = newState
    }

    fun onClickTurbulence() {
        if (shakingJob != null) {
            shakingJob?.cancel()
            shakingJob = null
            state.reduce { oldState ->
                oldState.copy(planeY = 50)
            }
            return
        }

        shakingJob = CoroutineScope(Dispatchers.Default).launch {

            while (coroutineContext.isActive) {
                delay(Random.nextLong(300, 500L))
                state.reduce { oldState ->
                    val y = Random.nextInt(50, 70)
                    oldState.copy(planeY = y)
                }
            }
        }

    }


}