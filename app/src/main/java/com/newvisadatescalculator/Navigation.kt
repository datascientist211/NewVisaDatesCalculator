package com.newvisadatescalculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
                navController.popBackStack()
            })
        }

        composable(
            route = Screen.TripDaysCalculator.route, arguments = listOf(navArgument("personUid") {
                type = NavType.IntType
            })
        ) {
            TripListRoute(onNavigateToAddTrip = { personUid ->
                navController.navigate(Screen.AddTrip.createRoute(personUid))
            }, onBackPress = {
                navController.popBackStack()
            })
        }

        composable(
            route = Screen.AddTrip.route, arguments = listOf(navArgument("personUid") {
                type = NavType.IntType
            })
        ) {
            AddTripRoute(onBackPressed = {
                navController.popBackStack()
            })
        }
    }
}

@Composable
fun VisaDatesAppBar(
    onBackPress: (() -> Unit)? = null,
    textTitle: String
) {
    TopAppBar(
        elevation = 4.dp,
        title = {
            Text(textTitle)
        },
        backgroundColor = MaterialTheme.colors.primarySurface,
        navigationIcon = {
            onBackPress?.let {
                IconButton(onClick = it) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            }
        })
}


@Composable
fun VisaDatesFAB(
    onClick: () -> Unit,
    textTitle: String
) {
    Box(modifier = Modifier.fillMaxSize()) {
        ExtendedFloatingActionButton(
            text = {
                Text(textTitle)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = textTitle,
                )
            },
            contentColor = Color.White,
            onClick = onClick,
            modifier = Modifier
                .padding(all = 16.dp)
                .align(alignment = Alignment.BottomEnd)
        )
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
