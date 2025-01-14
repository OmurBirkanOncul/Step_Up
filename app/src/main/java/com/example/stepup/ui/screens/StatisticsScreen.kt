package com.example.stepup.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.stepup.R
import com.example.stepup.ui.components.BottomNavBar
import com.example.stepup.ui.components.Sidebar
import kotlinx.coroutines.launch

@Composable
fun StatisticsScreen(
    navController: NavHostController,
    onThemeToggle: (Boolean) -> Unit,
    isDarkTheme: Boolean,
) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var selectedTime by remember { mutableStateOf("Last 7 days") }
    val currentScreen by remember { mutableStateOf("statistics") } // Seçili ekranı takip eden değişken

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
                    title = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 90.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Progress",
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
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                    modifier = Modifier.height(70.dp)
                )
            },
            bottomBar = {
                BottomNavBar(
                    navController = navController,
                    currentScreen = currentScreen,
                    onScreenSelected = { selectedScreen ->
                        navController.navigate(selectedScreen)
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Dropdown Menu
                Box(modifier = Modifier.fillMaxWidth()) {
                    TextButton(onClick = { /* Open dropdown logic */ }) {
                        Text(text = selectedTime, color = Color.Black, fontSize = 16.sp)
                    }
                }

                // Progress Summary
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Great Progress",
                        color = Color(0xFFFFA500),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "You're on track. Keep it up!",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }

                // Placeholder for Chart
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color(0xFF87CEEB)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Bar Chart Here", color = Color.White)
                }

                // Time Range Buttons
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = { selectedTime = "Last 2 weeks" },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF87CEEB))
                    ) {
                        Text("Last 2 weeks", color = Color.Black)
                    }
                    Button(
                        onClick = { selectedTime = "Last 1 month" },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF87CEEB))
                    ) {
                        Text("Last 1 month", color = Color.Black)
                    }
                    Button(
                        onClick = { selectedTime = "Last 1 week" },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF87CEEB))
                    ) {
                        Text("Last 1 week", color = Color.Black)
                    }
                }
            }
        }
    }
}

@Composable
fun CustomBottomNavBar(
    navController: NavController,
    currentScreen: String,
    onScreenSelected: (String) -> Unit
) {
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
            val items = listOf(
                Icons.Default.Home to "home",
                Icons.Default.BarChart to "statistics",
                Icons.Default.Check to "completed",
                Icons.Default.SkipNext to "skipped"
            )

            items.forEach { (icon, screen) ->
                IconButton(onClick = { onScreenSelected(screen) }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = screen.capitalize(),
                        tint = if (currentScreen == screen) Color.Cyan else Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}
