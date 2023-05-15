package com.newvisadatescalculator.screens

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AddTripScreen(
    onConfirmPressed: (String) -> Unit,
    onBackPressed: () -> Unit,
) {

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