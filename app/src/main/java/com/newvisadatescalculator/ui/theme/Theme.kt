package com.newvisadatescalculator.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun NewVisaDatesCalculatorTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = NewVisaDatesColors,
        typography = Typography(),
        shapes = Shapes(
            small = RoundedCornerShape(percent = 50),
            medium = RoundedCornerShape(size = 8.dp),
            large = RoundedCornerShape(size = 0.dp)
        ),
        content = content
    )
}