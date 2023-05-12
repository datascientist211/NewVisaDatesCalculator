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

@Composable
fun UserListScreen(
    onNavigateToAddUser: () -> Unit,
    onNavigateToTrip: () -> Unit,
    users: List<Person>
) {
    Column {
        val textString =
            stringResource(if (users.isEmpty()) R.string.empty_traveler_title else R.string.choose_traveler_title)

        Text(
            text = textString,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        )

        users.forEach { user ->
            user.name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.displayMedium,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth()
                )
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        ExtendedFloatingActionButton(
            onClick = onNavigateToAddUser,
            modifier = Modifier
                .padding(all = 16.dp)
                .align(alignment = Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "add user",
            )
            Text(
                text = stringResource(R.string.add_traveler),
            )
        }
    }
}