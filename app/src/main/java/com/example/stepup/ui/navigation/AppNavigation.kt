package com.example.stepup.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stepup.ui.screens.*
import com.example.stepup.ui.theme.StepUpTheme

@Composable
fun AppNavigation(navController: NavHostController, isDarkTheme: Boolean, onThemeToggle: (Boolean) -> Unit) {
    NavHost(navController = navController, startDestination = "home") {

        // Home Screen tam
        composable("home") {
            StepUpTheme(darkTheme = isDarkTheme) {
                HomeScreen(
                    onThemeToggle = onThemeToggle,
                    isDarkTheme = isDarkTheme,
                    navController = navController
                )
            }
        }

        // Profile Screen
        composable("profile") {
            StepUpTheme(darkTheme = isDarkTheme) {
                ProfileScreen(
                    onThemeToggle = onThemeToggle,
                    isDarkTheme = isDarkTheme,
                    navController = navController
                )
            }
        }

        // Terms of Use Screen tam
        composable("terms_of_use") {
            StepUpTheme(darkTheme = isDarkTheme) {
                TermsOfUseScreen(
                    onThemeToggle = onThemeToggle,
                    isDarkTheme = isDarkTheme,
                    navController = navController,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }

        // Statistics Screen
        composable("statistics") {
            StepUpTheme(darkTheme = isDarkTheme) {
                StatisticsScreen(
                    onThemeToggle = onThemeToggle,
                    isDarkTheme = isDarkTheme,
                    navController = navController
                )
            }
        }

        // Add Screen (FAB ile gidilen ekran)
        composable("add") {
            StepUpTheme(darkTheme = isDarkTheme) {
                AddScreen(
                    onThemeToggle = onThemeToggle,
                    isDarkTheme = isDarkTheme,
                    navController = navController
                )
            }
        }

        // Completed Tasks Screen
        composable("completed") {
            StepUpTheme(darkTheme = isDarkTheme) {
                CompletedScreen(
                    onThemeToggle = onThemeToggle,
                    isDarkTheme = isDarkTheme,
                    navController = navController
                )
            }
        }

        // Skipped Tasks Screen
        composable("skipped") {
            StepUpTheme(darkTheme = isDarkTheme) {
                SkippedScreen(
                    onThemeToggle = onThemeToggle,
                    isDarkTheme = isDarkTheme,
                    navController = navController
                )
            }
        }

        // Notifications Screen tam
        composable("notifications") {
            StepUpTheme(darkTheme = isDarkTheme) {
                NotificationsScreen(
                    onThemeToggle = onThemeToggle,
                    isDarkTheme = isDarkTheme,
                    navController = navController,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }

        // Privacy and Security Screen tam
        composable("privacy") {
            StepUpTheme(darkTheme = isDarkTheme) {
                PrivacyAndSecurityScreen(
                    onThemeToggle = onThemeToggle,
                    isDarkTheme = isDarkTheme,
                    navController = navController,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }

        // Delete Account Screen
        composable("delete_account") {
            StepUpTheme(darkTheme = isDarkTheme) {
                DeleteAccountScreen(
                    onThemeToggle = onThemeToggle,
                    isDarkTheme = isDarkTheme,
                    navController = navController
                )
            }
        }

        // About App Screen tam
        composable("about") {
            StepUpTheme(darkTheme = isDarkTheme) {
                AboutAppScreen(
                    onThemeToggle = onThemeToggle,
                    isDarkTheme = isDarkTheme,
                    navController = navController,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }

        //Notifications Agreement Screen
        composable("agreement") {
            StepUpTheme(darkTheme = isDarkTheme) {
                NotificationAgreementScreen(
                    navController = navController
                )
            }
        }

    }
}