package com.newvisadatescalculator.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.newvisadatescalculator.R
import com.visadatescalculator.model.Person
import com.visadatescalculator.model.Trip

@Composable
fun TripListScreen(
    onNavigateToAddTrip: (Int) -> Unit, trips: List<Trip>, person: Person?
) {
    person?.let { traveler ->
        Column {
            val textString = stringResource(
                if (trips.isEmpty()) R.string.empty_trips_title else R.string.press_calculation_title,
                traveler.name ?: stringResource(R.string.unknown_traveler_name)
            )

            Text(
                text = textString,
                style = MaterialTheme.typography.titleLarge,
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
                    style = MaterialTheme.typography.displayMedium,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth()
                )
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            ExtendedFloatingActionButton(
                onClick = { onNavigateToAddTrip(person.uid) },
                modifier = Modifier
                    .padding(all = 16.dp)
                    .align(alignment = Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add trip",
                )
                Text(
                    text = stringResource(R.string.add_trip),
                )
            }
        }
    }
}

@Composable
fun CalculationButton(onNavigateCalculationScreen: () -> Unit) {
    ExtendedFloatingActionButton(
        onClick = onNavigateCalculationScreen, modifier = Modifier.padding(all = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.calculate_days),
        )
    }
}
