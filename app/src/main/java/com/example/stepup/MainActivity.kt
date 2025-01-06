package com.example.stepup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.stepup.ui.screens.ContractScreen
import com.example.stepup.ui.theme.StepUpTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StepUpTheme {
               ContractScreen()
            }
        }
    }
}

