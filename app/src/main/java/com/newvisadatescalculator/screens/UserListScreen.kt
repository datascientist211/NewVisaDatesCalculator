package com.newvisadatescalculator.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.newvisadatescalculator.R

@Composable
fun UserListScreen(onNavigateToAddUser: (email: String) -> Unit,
                   onNavigateToTrip: (email: String) -> Unit
) {
    Text(
        text = stringResource(id = R.string.app_name),
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
    )
}