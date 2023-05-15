package com.newvisadatescalculator.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@Composable
fun AddUserScreen(
    onConfirmPressed: (String) -> Unit,
    onCancelPressed: () -> Unit,
) {
    var username by remember {
        mutableStateOf("")
    }

    AddUserDialog(
        title = {
            Text(
                text = "Enter traveler's name",
                style = MaterialTheme.typography.h5,
            )
        },
        content = {
            OutlinedTextField(value = username, onValueChange = { newText ->
                username = newText
            }, label = { Text(text = "Name") }, placeholder = { Text(text = "Enter your name") })
        },
        dismissButton = {
            TextButton(
                onClick = onCancelPressed,
                content = { Text("CANCEL") },
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmPressed(username)
                },
                content = { Text("OK") },
            )
        },
        onDismiss = onCancelPressed,
    )
}

@Composable
fun AddUserDialog(
    title: @Composable () -> Unit,
    content: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
    confirmButton: @Composable () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(onDismiss) {
        Surface(shape = MaterialTheme.shapes.medium) {
            Column {
                Column(Modifier.padding(24.dp)) {
                    title.invoke()
                    Spacer(Modifier.size(16.dp))
                    content.invoke()
                }
                Spacer(Modifier.size(4.dp))
                Row(
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    Arrangement.spacedBy(8.dp, Alignment.End),
                ) {
                    dismissButton.invoke()
                    confirmButton.invoke()
                }
            }
        }
    }
}