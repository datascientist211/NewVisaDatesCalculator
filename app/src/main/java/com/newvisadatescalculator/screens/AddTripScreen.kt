package com.newvisadatescalculator.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.newvisadatescalculator.R
import com.newvisadatescalculator.VisaDatesAppBar
import com.newvisadatescalculator.viewmodel.ErrorType
import org.joda.time.DateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTripScreen(
    onConfirmPressed: (DateTime?, DateTime?) -> Unit,
    onBackPressed: () -> Unit,
    errorType: ErrorType?,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        val state = rememberDateRangePickerState()

        VisaDatesAppBar(
            onBackPress = onBackPressed,
            onDonePressed = {
                val startMillisec = state.selectedStartDateMillis
                val endMillisec = state.selectedEndDateMillis
                val startDate = if (startMillisec != null) DateTime(startMillisec) else null
                val endDate = if (endMillisec != null) DateTime(endMillisec) else null
                onConfirmPressed(startDate, endDate)
            },
            textTitle = stringResource(R.string.new_trip_title)
        )



        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {

            val datePickerTitle = when (errorType) {
                ErrorType.INTERSECTION -> stringResource(R.string.intersection_error_text)
                ErrorType.EXCEPTION -> stringResource(R.string.exception_error_text)
                ErrorType.NO_PERIOD -> stringResource(R.string.no_period_error_text)
                else -> stringResource(R.string.choose_dates_trip_title)
            }

            val datePickerTitleColor = if (errorType == null) Color.Black else Color.Red
            DateRangePicker(
                title = {
                    Text(
                        text = datePickerTitle,
                        style = MaterialTheme.typography.titleLarge,
                        color = datePickerTitleColor
                    )
                },
                headline = {
                    Text(
                        text = "Start date - End date",
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                state = state,
                modifier = Modifier.weight(1f)
            )

        }
    }
}