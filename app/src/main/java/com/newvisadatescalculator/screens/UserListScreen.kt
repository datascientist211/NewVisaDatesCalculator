package com.newvisadatescalculator.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.newvisadatescalculator.R
import com.newvisadatescalculator.VisaDatesAppBar
import com.newvisadatescalculator.VisaDatesFAB
import com.visadatescalculator.model.Person

@Composable
fun UserListScreen(
    onNavigateToAddUser: () -> Unit,
    onNavigateToTrip: (Int) -> Unit,
    users: List<Person>
) {
    Column {
        VisaDatesAppBar(
            textTitle = stringResource(R.string.list_traveler_title)
        )

        val textString =
            stringResource(if (users.isEmpty()) R.string.empty_traveler_title else R.string.choose_traveler_title)

        Text(
            text = textString,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        )

        users.forEach { user ->
            TextButton(onClick = {
                onNavigateToTrip(user.uid)
            }) {
                Text(
                    text = user.name ?: stringResource(R.string.unknown_traveler_name),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth(),

                    )
            }
        }
    }

    VisaDatesFAB(
        onClick = onNavigateToAddUser,
        textTitle = stringResource(R.string.add_traveler)
    )
}