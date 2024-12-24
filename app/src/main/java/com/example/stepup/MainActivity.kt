package com.example.stepup

import com.example.stepup.ui.screens.PasswordRecoveryScreen //tamamlandı
import com.example.stepup.ui.screens.NotificationAgreementScreen //tamamlandı
import com.example.stepup.ui.screens.CreateAccountScreen //visible iconu dışında tamamlandı
import com.example.stepup.ui.screens.HomeScreen //tamamlandı
import com.example.stepup.ui.screens.TermsOfUseScreen
import androidx.activity.ComponentActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.stepup.ui.navigation.AppNavigation
import com.example.stepup.ui.theme.StepUpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkTheme by remember { mutableStateOf(false) }
            StepUpTheme(darkTheme = isDarkTheme) {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}