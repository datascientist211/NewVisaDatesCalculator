package com.newvisadatescalculator

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.newvisadatescalculator.routes.AddTripRoute
import com.newvisadatescalculator.routes.AddUserRoute
import com.newvisadatescalculator.routes.ChooseUserRoute
import com.newvisadatescalculator.routes.TripListRoute

@Composable
fun VisaDatesNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ChooseUser.route,
    ) {
        composable(Screen.ChooseUser.route) { backStackEntry ->
            ChooseUserRoute(
                onNavigateToAddUser = {
                    navController.navigate(Screen.NewUser.route)
                },
                onNavigateToTrip = { personUid ->
                    if (backStackEntry.getLifecycle().currentState == Lifecycle.State.RESUMED) {
                        navController.navigate(Screen.TripDaysCalculator.createRoute(personUid))
                    }
                },
            )
        }

        composable(Screen.NewUser.route) {
            AddUserRoute(onDonePressed = {
                navController.popBackStack(Screen.ChooseUser.route, false)
            })
        }

        composable(
            route = Screen.TripDaysCalculator.route,
            arguments = listOf(navArgument("personUid") {
                type = NavType.IntType
            })
        ) {
            TripListRoute(onNavigateToAddTrip = { personUid ->
                navController.navigate(Screen.AddTrip.createRoute(personUid))
            })
        }

        composable(
            route = Screen.AddTrip.route,
            arguments = listOf(navArgument("personUid") {
                type = NavType.IntType
            })
        ) {
            AddTripRoute(
                onBackPressed = { navController.popBackStack(Screen.ChooseUser.route, false) }
            )
        }
    }
}

sealed class Screen(val route: String) {
    object ChooseUser : Screen("home")
    object TripDaysCalculator : Screen("trips/{personUid}") {
        fun createRoute(personUid: Int) = "trips/$personUid"
    }

    object NewUser : Screen("new_user")

    object AddTrip : Screen("add_trip/{personUid}") {
        fun createRoute(personUid: Int) = "add_trip/$personUid"
    }
}
