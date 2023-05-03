package com.newvisadatescalculator.routes

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.newvisadatescalculator.screens.AddUserScreen
import com.visadatescalculator.viewmodel.AddUserViewModel
import com.visadatescalculator.viewmodel.AddUserViewModelFactory


@Composable
fun AddUserRoute(
    onDonePressed: () -> Unit,
) {

    val addUserViewModel: AddUserViewModel = viewModel(factory = AddUserViewModelFactory())

    AddUserScreen(
        onConfirmPressed = { name ->
            addUserViewModel.addUser(name)
            onDonePressed()
        },
        onCancelPressed = { onDonePressed() },
    )
}