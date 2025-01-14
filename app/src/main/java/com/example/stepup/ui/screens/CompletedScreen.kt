package com.example.stepup.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stepup.R
import com.example.stepup.models.Task
import com.example.stepup.ui.components.BottomNavBar
import com.example.stepup.ui.components.Sidebar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun CompletedScreen(
    navController: NavHostController,
    onThemeToggle: (Boolean) -> Unit,
    isDarkTheme: Boolean
) {
    var currentScreen by remember { mutableStateOf("home") }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val db = remember { FirebaseFirestore.getInstance() }
    var taskList by remember { mutableStateOf<List<Task>>(emptyList()) }

    LaunchedEffect(Unit) {
        taskList = getCompletedTasks(db)
    }

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
                        Text(
                            text = "Completed Tasks",
                            color = MaterialTheme.colorScheme.tertiary,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
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
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
                )
            },
            bottomBar = {
                BottomNavBar(
                    navController = navController,
                    currentScreen = "completed",
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
                taskList.forEach { task ->
                    TaskCard(title = task.title, subtitle = task.description)
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
        }
    }
}

suspend fun getCompletedTasks(db: FirebaseFirestore): List<Task> {
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return emptyList()
    return db.collection("profiles")
        .document(userId)
        .collection("tasks")
        .whereEqualTo("completed", true)
        .get()
        .await()
        .toObjects(Task::class.java)
}

