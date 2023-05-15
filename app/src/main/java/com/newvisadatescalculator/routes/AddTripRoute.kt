package com.newvisadatescalculator.routes

import androidx.compose.runtime.Composable
import com.newvisadatescalculator.screens.AddTripScreen

@Composable
fun AddTripRoute(
    onBackPressed: () -> Unit
    /*TODO: add viewmodel*/
) {

    AddTripScreen(
        onConfirmPressed = {},
        onBackPressed = onBackPressed,
    )
}