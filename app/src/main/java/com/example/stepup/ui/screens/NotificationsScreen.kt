package com.example.stepup.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.stepup.R
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stepup.helpers.PreferencesHelper
import com.example.stepup.helpers.NotificationService
import com.example.stepup.ui.components.Sidebar

@Composable
fun NotificationsScreen(
    navController: NavHostController,
    onThemeToggle: (Boolean) -> Unit,
    isDarkTheme: Boolean,
    onBackClick: () -> Unit,
    context: Context
) {
    val preferencesHelper = PreferencesHelper(context)
    var areNotificationsEnabled by remember {
        mutableStateOf(preferencesHelper.areNotificationsEnabled())
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Sidebar(
                onThemeToggle = onThemeToggle,
                isDarkTheme = isDarkTheme,
                navController = navController,
                currentScreen = "notifications"
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 60.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.notification),
                                    contentDescription = "Privacy Icon",
                                    tint = MaterialTheme.colorScheme.tertiary,
                                    modifier = Modifier.size(25.dp)
                                )
                                Text(
                                    text = "Notifications",
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    },
                    navigationIcon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            IconButton(onClick = {
                                scope.launch {
                                    if (drawerState.isClosed) drawerState.open() else drawerState.close()
                                }
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.sidebar),
                                    contentDescription = "Menu Icon",
                                    modifier = Modifier.size(24.dp),
                                    tint = MaterialTheme.colorScheme.tertiary
                                )
                            }
                            IconButton(onClick = { navController.navigate("home") }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                    modifier = Modifier.height(70.dp)
                )
            },
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "StepUp sends notifications to remind you of your daily habits, motivate progress, and encourage consistency. You can customize the type, frequency, and timing of notifications based on your preferences. Notifications can be enabled or disabled anytime through the app's settings, allowing you to control which reminders you receive. By using StepUp, you agree to receive notifications to support your habit-building journey.",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (areNotificationsEnabled) "Notifications Enabled" else "Notifications Disabled",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Switch(
                                checked = areNotificationsEnabled,
                                onCheckedChange = { isChecked ->
                                    areNotificationsEnabled = isChecked
                                    preferencesHelper.setNotificationsEnabled(isChecked)

                                    if (isChecked) {
                                        // Servisi başlat
                                        context.startService(
                                            Intent(context, NotificationService::class.java)
                                        )
                                    } else {
                                        // Servisi durdur
                                        context.stopService(
                                            Intent(context, NotificationService::class.java)
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}
