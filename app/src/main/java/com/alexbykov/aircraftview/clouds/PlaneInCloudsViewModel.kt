package com.alexbykov.aircraftview.clouds

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PlaneInCloudsViewModel : ViewModel() {

    private val state: MutableState<PlaneInCloudsContract.ScreenState> =
        mutableStateOf(PlaneInCloudsContract.createInitialState())


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

            when (oldState.theme) {
                is PlaneInCloudsContract.Theme.Day -> {
                    changeThemeButtonText="Night"
                    newTheme=PlaneInCloudsContract.Theme.Night
                }
                is PlaneInCloudsContract.Theme.Night -> {
                    changeThemeButtonText="Day"
                    newTheme=PlaneInCloudsContract.Theme.Day
                }
            }

            oldState.copy(
                theme = newTheme,
                themeChangeText = changeThemeButtonText
            )
        }
    }


    private inline fun <T> MutableState<T>.reduce(
        reducer: (currentState: T) -> T
    ) {
        val newState = reducer.invoke(value)
        value = newState
    }


}