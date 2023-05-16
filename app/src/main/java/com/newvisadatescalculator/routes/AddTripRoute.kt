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
    onTripSaved: (Int) -> Unit,
    addTripViewModel: AddTripViewModel = hiltViewModel()
) {

    val viewState by addTripViewModel.state.collectAsStateWithLifecycle()

    if (viewState.newTrip != null) {
        onBackPressed()
        //onTripSaved(viewState.personUid)
    } else {
        AddTripScreen(
            onConfirmPressed = { startDate, endDate ->
                addTripViewModel.addTrip(startDate, endDate)
            },
            onBackPressed = onBackPressed,
            errorType = viewState.errorType
        )
    }
}