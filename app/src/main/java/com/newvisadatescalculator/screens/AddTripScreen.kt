package com.newvisadatescalculator.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.newvisadatescalculator.R
import com.newvisadatescalculator.VisaDatesAppBar

@Composable
fun AddTripScreen(
    onConfirmPressed: (String) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        VisaDatesAppBar(
            onBackPress = onBackPressed,
            textTitle = stringResource(R.string.new_trip_title)
        )

        Text(
            text = "Here will be UI for adding new trip",
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}