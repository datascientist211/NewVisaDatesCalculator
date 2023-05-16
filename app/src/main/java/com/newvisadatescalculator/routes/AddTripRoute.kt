package com.newvisadatescalculator.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.newvisadatescalculator.screens.AddTripScreen
import com.newvisadatescalculator.viewmodel.AddTripViewModel

@Composable
fun AddTripRoute(
    onBackPressed: () -> Unit,
    addTripViewModel: AddTripViewModel = hiltViewModel()
) {

    val viewState by addTripViewModel.state.collectAsStateWithLifecycle()

    AddTripScreen(
        onConfirmPressed = { enterDate, leaveDate ->
            enterDate?.let { startDate ->
                viewState.enterDate = startDate
                leaveDate?.let { endDate ->
                    viewState.leaveDate = endDate
                    addTripViewModel.addTrip()
                }
            }
        },
        onBackPressed = onBackPressed,
    )
}