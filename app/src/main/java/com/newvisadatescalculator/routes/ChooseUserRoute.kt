package com.newvisadatescalculator.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.newvisadatescalculator.screens.UserListScreen
import com.visadatescalculator.viewmodel.ChooseUserViewModel
import com.visadatescalculator.viewmodel.ChooseUserViewModelFactory

@Composable
fun ChooseUserRoute(
    onNavigateToAddUser: () -> Unit,
    onNavigateToTrip: () -> Unit,
    viewModel: ChooseUserViewModel = viewModel(factory = ChooseUserViewModelFactory())
) {

    val viewState by viewModel.state.collectAsStateWithLifecycle()

    UserListScreen(
        onNavigateToAddUser = onNavigateToAddUser,
        onNavigateToTrip = onNavigateToTrip,
        viewState.users
    )
}