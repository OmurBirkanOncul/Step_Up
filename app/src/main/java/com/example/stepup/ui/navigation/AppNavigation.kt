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
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {

        // Home Screen
        composable("home") {
            var isDarkTheme by remember { mutableStateOf(false) }
            StepUpTheme(darkTheme = isDarkTheme) {
                HomeScreen(
                    onThemeToggle = { isDarkTheme = it },
                    isDarkTheme = isDarkTheme,
                    navController = navController
                )
            }

            // Profile Screen
            composable("profile") {
                StepUpTheme(darkTheme = isDarkTheme) {
                    ProfileScreen(
                        onThemeToggle = { isDarkTheme = it },
                        isDarkTheme = isDarkTheme,
                        navController = navController
                    )
                }
            }

            // Terms of Use Screen
            composable("terms_of_use") {
                StepUpTheme(darkTheme = isDarkTheme) {
                    TermsOfUseScreen(
                        onThemeToggle = { isDarkTheme = it },
                        isDarkTheme = isDarkTheme,
                        navController = navController
                    )
                }
            }

            // Statistics Screen
            composable("statistics") {
                StepUpTheme(darkTheme = isDarkTheme) {
                    StatisticsScreen(
                        onThemeToggle = { isDarkTheme = it },
                        isDarkTheme = isDarkTheme,
                        navController = navController
                    )
                }
            }

            // Add Screen (FAB ile gidilen ekran)
            composable("add") {
                StepUpTheme(darkTheme = isDarkTheme) {
                    AddScreen(
                        onThemeToggle = { isDarkTheme = it },
                        isDarkTheme = isDarkTheme,
                        navController = navController
                    )
                }
            }

            // Completed Tasks Screen
            composable("completed") {
                StepUpTheme(darkTheme = isDarkTheme) {
                    CompletedScreen(
                        onThemeToggle = { isDarkTheme = it },
                        isDarkTheme = isDarkTheme,
                        navController = navController
                    )
                }
            }

            // Skipped Tasks Screen
            composable("skipped") {
                StepUpTheme(darkTheme = isDarkTheme) {
                    SkippedScreen(
                        onThemeToggle = { isDarkTheme = it },
                        isDarkTheme = isDarkTheme,
                        navController = navController
                    )
                }
            }

            // Notifications Screen
            composable("notifications") {
                StepUpTheme(darkTheme = isDarkTheme) {
                    NotificationsScreen(
                        onThemeToggle = { isDarkTheme = it },
                        isDarkTheme = isDarkTheme,
                        navController = navController
                    )
                }
            }

            // Privacy and Security Screen
            composable("privacy") {
                StepUpTheme(darkTheme = isDarkTheme) {
                    PrivacyAndSecurityScreen(
                        onThemeToggle = { isDarkTheme = it },
                        isDarkTheme = isDarkTheme,
                        navController = navController
                    )
                }
            }

            // Delete Account Screen
            composable("delete_account") {
                StepUpTheme(darkTheme = isDarkTheme) {
                    DeleteAccountScreen(
                        onThemeToggle = { isDarkTheme = it },
                        isDarkTheme = isDarkTheme,
                        navController = navController
                    )
                }
            }

            // About App Screen
            composable("about") {
                StepUpTheme(darkTheme = isDarkTheme) {
                    AboutAppScreen(
                        onThemeToggle = { isDarkTheme = it },
                        isDarkTheme = isDarkTheme,
                        navController = navController
                    )
                }
            }
        }
    }
}