package com.newvisadatescalculator.routes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.newvisadatescalculator.screens.AddUserScreen
import com.visadatescalculator.viewmodel.AddUserViewModel


@Composable
fun AddUserRoute(
    onDonePressed: () -> Unit,
    addUserViewModel: AddUserViewModel = hiltViewModel()
) {

    AddUserScreen(
        onConfirmPressed = { name ->
            addUserViewModel.addUser(name)
            onDonePressed()
        },
        onCancelPressed = { onDonePressed() },
    )
}