package com.newvisadatescalculator.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.newvisadatescalculator.screens.UserListScreen
import com.visadatescalculator.viewmodel.ChooseUserViewModel

@Composable
fun ChooseUserRoute(
    onNavigateToAddUser: () -> Unit,
    onNavigateToTrip: () -> Unit,
    viewModel: ChooseUserViewModel = hiltViewModel()
) {

    val viewState by viewModel.state.collectAsStateWithLifecycle()

    UserListScreen(
        onNavigateToAddUser = onNavigateToAddUser,
        onNavigateToTrip = onNavigateToTrip,
        viewState.users
    )
}