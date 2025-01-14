package com.example.stepup.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stepup.R
import com.example.stepup.ui.components.BottomNavBar
import com.example.stepup.ui.components.Sidebar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavHostController, onThemeToggle: (Boolean) -> Unit, isDarkTheme: Boolean) {
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var currentScreen by remember { mutableStateOf("profile") }
    var isEditing by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var aboutMe by remember { mutableStateOf("") }

    val userId = auth.currentUser?.uid

    // Firestore'dan verileri çekme
    LaunchedEffect(userId) {
        if (userId != null) {
            db.collection("profiles").document(userId).get()
                .addOnSuccessListener { document ->
                    name = document.getString("name") ?: ""
                    username = document.getString("username") ?: ""
                    phoneNumber = document.getString("phoneNumber") ?: ""
                    aboutMe = document.getString("aboutMe") ?: ""
                }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Sidebar(
                onThemeToggle = onThemeToggle,
                isDarkTheme = isDarkTheme,
                navController = navController,
                currentScreen = "profile"
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
                            Text(
                                text = "My Profile",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    },
                    navigationIcon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
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
            bottomBar = {
                BottomNavBar(
                    navController = navController,
                    currentScreen = "profile",
                    onScreenSelected = { selectedScreen ->
                        currentScreen = selectedScreen
                        navController.navigate(selectedScreen)
                    }
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
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.profile),
                                contentDescription = "Profile Picture",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(100.dp).clip(CircleShape)
                            )
                        }

                        Spacer(modifier = Modifier.height(50.dp))

                        if (isEditing) {
                            EditableUserProfileField(label = "What's your name?", value = name, onValueChange = { name = it })
                            EditableUserProfileField(label = "What's your username?", value = username, onValueChange = { username = it })
                            EditableUserProfileField(label = "Phone number", value = phoneNumber, onValueChange = { phoneNumber = it })
                            EditableUserProfileField(label = "About Me", value = aboutMe, onValueChange = { aboutMe = it })

                            Button(
                                onClick = {
                                    if (userId != null) {
                                        val updatedData = mapOf(
                                            "name" to name,
                                            "username" to username,
                                            "phoneNumber" to phoneNumber,
                                            "aboutMe" to aboutMe
                                        )
                                        db.collection("profiles").document(userId)
                                            .update(updatedData)
                                            .addOnSuccessListener {
                                                isEditing = false
                                            }
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                modifier = Modifier.padding(top = 16.dp)
                            ) {
                                Text("Save", color = Color.White)
                            }
                        } else {
                            UserProfileField(label = "Name", value = name)
                            UserProfileField(label = "Username", value = username)
                            UserProfileField(label = "Phone Number", value = phoneNumber)
                            UserProfileField(label = "About Me", value = aboutMe)

                            Button(
                                onClick = { isEditing = true },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                modifier = Modifier.padding(top = 16.dp)
                            ) {
                                Text("Edit Profile", color = Color.White)
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun UserProfileField(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f), shape = MaterialTheme.shapes.medium)
        ) {
            Text(
                text = value,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun EditableUserProfileField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(fontSize = 14.sp)
        )
    }
}
