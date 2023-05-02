package com.newvisadatescalculator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.newvisadatescalculator.routes.ChooseUserRoute

@Composable
fun VisaDatesNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ChooseUser.route,
    ) {
        composable(Screen.ChooseUser.route) {
            ChooseUserRoute(
                onNavigateToAddUser = {
                    navController.navigate(Screen.NewUser.route)
                },
                onNavigateToTrip = {
                    navController.navigate(Screen.TripList.route)
                },
            )
        }
}
}

sealed class Screen(val route: String) {
    object ChooseUser : Screen("home")
    object TripList : Screen("trip_list")
    object NewUser : Screen("new_user")
}
