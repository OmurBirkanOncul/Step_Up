package com.example.stepup.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stepup.ui.screens.*
import com.example.stepup.ui.theme.StepUpTheme
import com.example.stepup.models.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun AppNavigation(
    navController: NavHostController,
    isDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit
) {
    // Shared task list
    val taskList = remember { mutableStateListOf<Task>() }

    NavHost(navController = navController, startDestination = "onboarding") {

        composable("onboarding") {
            StepUpTheme(darkTheme = isDarkTheme) {
                OnboardingScreen(navController = navController)
            }
        }

        // Home Screen
        composable("home") {
            StepUpTheme(darkTheme = isDarkTheme) {
                HomeScreen(
                    onThemeToggle = onThemeToggle,
                    isDarkTheme = isDarkTheme,
                    navController = navController,
                    taskList = taskList // Passing shared task list
                )
            }
        }

        // Add Task Title Screen
        composable("add_task_title") {
            AddTaskTitleScreen(
                navController = navController,
                onTitleEntered = { title ->
                    // Navigate to the next screen with title as argument
                    navController.navigate("choose_time?taskTitle=$title")
                }
            )
        }

        // Choose Time and Description Screen
        composable("choose_time?taskTitle={taskTitle}") { backStackEntry ->
            val taskTitle = backStackEntry.arguments?.getString("taskTitle") ?: ""
            TimeAndDescScreen(
                navController = navController,
                taskTitle = taskTitle,
                onDescriptionEntered = { description ->
                    navController.navigate("choose_icon?taskTitle=$taskTitle&description=$description")
                }
            )
        }

        // Choose Icon Screen
        composable("choose_icon?taskTitle={taskTitle}&description={description}") { backStackEntry ->
            val taskTitle = backStackEntry.arguments?.getString("taskTitle") ?: ""
            val description = backStackEntry.arguments?.getString("description") ?: ""
            val coroutineScope = rememberCoroutineScope()
            ChooseIconScreen(
                navController = navController,
                onTaskSave = { task ->
                    taskList.add(task)
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                    coroutineScope.launch {
                        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid ?: return@launch

                        val db = FirebaseFirestore.getInstance()
                            .collection("profiles")
                            .document(currentUserId)
                            .collection("tasks")
                            .document()



                        val taskWithId = task.copy(id = db.id)

                        db.set(taskWithId).addOnFailureListener {
                            println("Error adding task")
                            println(it.cause)
                        }.addOnCompleteListener {
                            println("Complete task")
                            println(it.exception)
                            println(it.isSuccessful)
                        }.await()


                    }



                },
                taskTitle = taskTitle,
                description = description
            )
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

        // Terms of Use Screen
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

        // Notifications Screen
        composable("notifications") {
            StepUpTheme(darkTheme = isDarkTheme) {
                NotificationsScreen(
                    onThemeToggle = onThemeToggle,
                    isDarkTheme = isDarkTheme,
                    navController = navController,
                    onBackClick = { navController.popBackStack() },
                    context = LocalContext.current
                )
            }
        }

        // Privacy and Security Screen
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

        // About App Screen
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

        // Notifications Agreement Screen
        composable("agreement") {
            StepUpTheme(darkTheme = isDarkTheme) {
                NotificationAgreementScreen(navController = navController)
            }
        }

        // LoginScreenn
        composable("start") {
            StepUpTheme(darkTheme = isDarkTheme) {
                StepUpScreen(
                    navController = navController
                )
            }
        }

        // LoginScreen
        composable("login") {
            StepUpTheme(darkTheme = isDarkTheme) {
                LoginScreen(
                    navController = navController,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }

        // Create Account Screen
        composable("newaccount") {
            StepUpTheme(darkTheme = isDarkTheme) {
                CreateAccountScreen (
                    navController = navController,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }

        // Create Profile
        composable("createprofile") {
            StepUpTheme(darkTheme = isDarkTheme) {
                CreateProfileScreen(
                    navController = navController
                )
            }
        }

        // Create Account
        composable("create_account") {
            StepUpTheme(darkTheme = isDarkTheme) {
                AccountCreatedScreen(
                    navController = navController
                )
            }
        }

        // Contract File
        composable("contract") {
            StepUpTheme(darkTheme = isDarkTheme) {
                ContractScreen(
                    navController = navController
                )
            }
        }

        // Password Recovery
        composable("password_recovery") {
            StepUpTheme(darkTheme = isDarkTheme) {
                PasswordRecoveryScreen(
                    navController = navController,
                    onBackClick = { navController.popBackStack() },
                    email = String.toString()
                )
            }
        }
    }
}