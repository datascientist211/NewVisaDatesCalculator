package com.newvisadatescalculator.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUserScreen(
    onConfirmPressed: () -> Unit,
    onCancelPressed: () -> Unit,
) {
    AddUserDialog(
        title = {
            Text(
                text = "Enter traveler's name",
                style = MaterialTheme.typography.titleLarge,
            )
        },
        content = {
            OutlinedTextField(
                value = "",
                onValueChange = { })
        },
        dismissButton = {
            TextButton(
                onClick = onCancelPressed,
                content = { Text("CANCEL") },
            )
        },
        confirmButton = {
            TextButton(
                onClick = onConfirmPressed,
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