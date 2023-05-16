package com.newvisadatescalculator.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.newvisadatescalculator.R
import com.newvisadatescalculator.VisaDatesAppBar
import org.joda.time.DateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTripScreen(
    onConfirmPressed: (DateTime?, DateTime?) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {

    var enterDate: DateTime? by remember {
        mutableStateOf(null)
    }

    var leaveDate: DateTime? by remember {
        mutableStateOf(null)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {


        VisaDatesAppBar(
            onBackPress = onBackPressed,
            textTitle = stringResource(R.string.new_trip_title)
        )

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            val state = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
            DatePicker(state = state, modifier = Modifier.padding(16.dp))

            Text("Entered date timestamp: ${state.selectedDateMillis ?: "no input"}")
        }

        Text(
            text = "Here will be UI for adding new trip",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}