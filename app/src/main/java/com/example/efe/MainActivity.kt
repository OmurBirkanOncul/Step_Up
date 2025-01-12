package com.example.efe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.efe.ui.theme.StepUpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StepUpTheme {
                val navController = rememberNavController()
                val isDarkTheme = remember { mutableStateOf(false) } // Tema durumu
                val onThemeToggle: (Boolean) -> Unit = { isDarkTheme.value = it } // Tema değişimi

                // CompletedTasksScreen çağrısı
                CompletedTasksScreen(
                    onThemeToggle = onThemeToggle,
                    isDarkTheme = isDarkTheme.value,
                    navController = navController
                )
            }
        }
    }
}
