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
                state.update { oldState ->
                    oldState.copy(
                        animationState = PlaneInCloudsContract.AnimationState.Idle,
                        controlButtonText = "Let's fly"
                    )
                }
            }
            PlaneInCloudsContract.AnimationState.Idle -> {
                state.update { oldState ->
                    oldState.copy(
                        animationState = PlaneInCloudsContract.AnimationState.Animating,
                        controlButtonText = "Stop"
                    )
                }
            }
        }
    }


    private inline fun <T> MutableState<T>.update(
        reducer: (currentState: T) -> T
    ) {
        val newState = reducer.invoke(value)
        value = newState
    }


}