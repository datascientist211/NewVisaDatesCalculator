package com.newvisadatescalculator.routes

import androidx.compose.runtime.Composable
import com.newvisadatescalculator.screens.AddUserScreen


@Composable
fun AddUserRoute(
    onDonePressed: () -> Unit,
) {

    AddUserScreen(
        onConfirmPressed = { onDonePressed() },
        onCancelPressed = { onDonePressed() },
    )
}