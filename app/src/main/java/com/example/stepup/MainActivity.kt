package com.example.stepup

import com.example.stepup.ui.screens.PasswordRecoveryScreen
import com.example.stepup.ui.screens.NotificationAgreementScreen
import com.example.stepup.ui.screens.CreateAccountScreen
import androidx.activity.ComponentActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.stepup.ui.navigation.AppNavigation
import com.example.stepup.ui.theme.StepUpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }
            StepUpTheme(darkTheme = isDarkTheme) {
                val navController = rememberNavController()
                AppNavigation(
                    navController = navController,
                    isDarkTheme = isDarkTheme,
                    onThemeToggle = { isDarkTheme = it })
            }
        }
    }
}

