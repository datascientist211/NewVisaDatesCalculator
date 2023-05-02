package com.newvisadatescalculator.routes

import androidx.compose.runtime.Composable
import com.newvisadatescalculator.screens.UserListScreen

@Composable
fun ChooseUserRoute(
    onNavigateToAddUser: (email: String) -> Unit,
    onNavigateToTrip: (email: String) -> Unit
) {
    //val chooseUserViewModel: ChooseUserViewModel = viewModel(factory = ChooseUserViewModelFactory())

    UserListScreen(
        onNavigateToAddUser = onNavigateToAddUser,
        onNavigateToTrip = onNavigateToTrip,
    )
}