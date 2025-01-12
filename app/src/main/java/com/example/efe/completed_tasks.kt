package com.example.efe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stepup.ui.components.BottomNavBar
import com.example.stepup.ui.components.Sidebar
import kotlinx.coroutines.launch

@Composable
fun CompletedTasksScreen(
    navController: NavHostController,
    onThemeToggle: (Boolean) -> Unit,
    isDarkTheme: Boolean
) {
    var currentScreen by remember { mutableStateOf("home") }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Sidebar(
                onThemeToggle = onThemeToggle,
                isDarkTheme = isDarkTheme,
                navController = navController,
                currentScreen = currentScreen
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Completed Tasks", color = MaterialTheme.colorScheme.primary) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    if (drawerState.isClosed) drawerState.open()
                                    else drawerState.close()
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.sidebar),
                                contentDescription = "Menu Icon",
                                modifier = Modifier.size(24.dp),
                                tint = MaterialTheme.colorScheme.tertiary
                            )
                        }
                    }
                )
            },
            bottomBar = {
                BottomNavBar(
                    navController = navController,
                    currentScreen = currentScreen,
                    onScreenSelected = { selectedScreen ->
                        currentScreen = selectedScreen
                        navController.navigate(selectedScreen)
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val tasks = listOf(
                    "Turn off your phone" to "Take a breath!",
                    "Take a break" to "One more step—let’s crush it!",
                    "Do a digital file cleanup" to "Strong habits, stronger you!",
                    "Assign tasks to members" to "Dream big, act small!",
                    "Follow-Up after meetings" to "Your body will thank you tomorrow!",
                    "Set focus your project" to "Every day is a fresh start!",
                    "Attend Conferences" to "Small steps lead to big changes!"
                )

                tasks.forEach { (title, subtitle) ->
                    TaskCard(title = title, subtitle = subtitle)
                }
            }
        }
    }
}

@Composable
fun TaskCard(title: String, subtitle: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(title, color = Color.White, fontSize = 18.sp)
                Text(subtitle, color = Color.LightGray, fontSize = 14.sp)
            }
            IconButton(onClick = { /* Complete Task Action */ }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Complete",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun CustomBottomNavBarForCompletedTasks() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Navigate to Home */ }) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
            IconButton(onClick = { /* Navigate to Statistics */ }) {
                Icon(
                    imageVector = Icons.Default.BarChart,
                    contentDescription = "Statistics",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
            IconButton(onClick = { /* Navigate to Completed */ }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Completed",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
            IconButton(onClick = { /* Navigate to Skipped */ }) {
                Icon(
                    imageVector = Icons.Default.SkipNext,
                    contentDescription = "Skipped",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}
