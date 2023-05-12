package com.newvisadatescalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.newvisadatescalculator.ui.theme.NewVisaDatesCalculatorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewVisaDatesCalculatorTheme {
                VisaDatesNavHost()
            }
        }
    }
}