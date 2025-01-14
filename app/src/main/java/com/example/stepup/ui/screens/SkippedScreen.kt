package com.example.stepup.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun SkippedScreen(
    navController: NavHostController,
    onThemeToggle: (Boolean) -> Unit,
    isDarkTheme: Boolean
) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var currentScreen by remember { mutableStateOf("skipped") }
    val db = remember { FirebaseFirestore.getInstance() }
    var taskList by remember { mutableStateOf<List<Task>>(emptyList()) }

    LaunchedEffect(Unit) {
        taskList = getSkippedTasks(db)
        println("Skipped Tasks: $taskList") // Konsolda kontrol edin
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
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 70.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Skipped Tasks",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    },
                    navigationIcon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(onClick = {
                                scope.launch {
                                    if (drawerState.isClosed) drawerState.open() else drawerState.close()
                                }
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.sidebar),
                                    contentDescription = "Menu Icon",
                                    tint = MaterialTheme.colorScheme.tertiary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    modifier = Modifier.height(70.dp)
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
            SkippedTaskList(taskLists = taskList, modifier = Modifier.padding(paddingValues))
        }
    }
}

@Composable
fun SkippedTaskList(taskLists: List<Task>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        taskLists.forEach { task ->
            SkippedTaskCard(title = task.title, subtitle = task.description)
        }
    }
}

@Composable
fun SkippedTaskCard(title: String, subtitle: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Add Action */ },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Yellow, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("T", fontWeight = FontWeight.Bold, color = Color.Black)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(title, color = Color.Yellow, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(subtitle, color = Color.Gray, fontSize = 14.sp)
            }
        }
    }
}
suspend fun getSkippedTasks(db: FirebaseFirestore): List<Task> {
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return emptyList()
    return db.collection("profiles")
        .document(userId)
        .collection("tasks")
        .whereEqualTo("skipped", true) // Firestore'daki "skipped" alanına göre filtreleme
        .get()
        .await()
        .toObjects(Task::class.java)
}
