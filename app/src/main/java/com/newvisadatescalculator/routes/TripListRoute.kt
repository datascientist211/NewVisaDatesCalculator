package com.newvisadatescalculator.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.newvisadatescalculator.screens.TripListScreen
import com.visadatescalculator.viewmodel.DaysCalculatorViewModel

@Composable
fun TripListRoute(
    onNavigateToAddTrip: (Int) -> Unit,
    viewModel: DaysCalculatorViewModel = hiltViewModel()
) {

    val viewState by viewModel.state.collectAsStateWithLifecycle()

    TripListScreen(
        onNavigateToAddTrip = onNavigateToAddTrip,
        viewState.trips,
        viewState.user
    )
}