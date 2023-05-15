package com.newvisadatescalculator.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.newvisadatescalculator.R
import com.newvisadatescalculator.VisaDatesAppBar
import com.newvisadatescalculator.VisaDatesFAB
import com.visadatescalculator.model.Person
import com.visadatescalculator.model.Trip

@Composable
fun TripListScreen(
    onNavigateToAddTrip: (Int) -> Unit,
    onBackPress: () -> Unit,
    trips: List<Trip>,
    person: Person?,
    modifier: Modifier = Modifier
) {
    person?.let { traveler ->

        val travelerName = traveler.name ?: stringResource(R.string.unknown_traveler_name)

        Column(
            modifier = modifier
                .fillMaxSize()
        ) {

            VisaDatesAppBar(
                onBackPress = onBackPress,
                textTitle = stringResource(R.string.trip_list_title, travelerName)
            )

            val textString = stringResource(
                if (trips.isEmpty()) R.string.empty_trips_title else R.string.press_calculation_title,
                travelerName
            )

            Text(
                text = textString,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth()
            )

            if (trips.isNotEmpty()) {
                CalculationButton {
                    /* TODO */
                }
            }

            trips.forEach { trip ->
                Text(
                    text = trip.uid.toString(),
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth()
                )
            }
        }

        VisaDatesFAB(
            onClick = { onNavigateToAddTrip(person.uid) },
            textTitle = stringResource(R.string.add_trip)
        )
    }
}

@Composable
fun CalculationButton(onNavigateCalculationScreen: () -> Unit) {
    ExtendedFloatingActionButton(
        text = {
            Text(stringResource(R.string.calculate_days))
        },
        onClick = onNavigateCalculationScreen,
        modifier = Modifier.padding(all = 16.dp)
    )
}
