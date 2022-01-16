package com.alexbykov.aircraftview.clouds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.alexbykov.aircraftview.ui.theme.AircraftViewTheme

class PlaneInCloudsActivity : ComponentActivity() {

    private val viewModel by viewModels<PlaneInCloudsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AircraftViewTheme {
                AircraftInCloudsScreen(viewModel)
            }
        }
    }
}