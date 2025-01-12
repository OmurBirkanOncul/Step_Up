package com.example.stepup.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavHostController, onThemeToggle: (Boolean) -> Unit, isDarkTheme: Boolean) {
    var currentScreen by remember { mutableStateOf("profile") }
    var isEditing by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("muge.irem") }
    var username by remember { mutableStateOf("@Neokineo") }
    var phoneNumber by remember { mutableStateOf("+90 53") }
    var aboutMe by remember { mutableStateOf("Hi, I’m Muge, a 33-year-old IT Project Manager from Ankara.") }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

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
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "My Profile",
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
                        } else {
                            UserProfileField(label = "What's your name?", value = name)
                            UserProfileField(label = "What's your username?", value = username)
                            UserProfileField(label = "Phone number", value = phoneNumber)
                            UserProfileField(label = "About Me", value = aboutMe)
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            if (isEditing) {
                                Button(
                                    onClick = { isEditing = false },
                                    shape = MaterialTheme.shapes.extraLarge,
                                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                                ) {
                                    Text(text = "Save", color = Color.White)
                                }
                            }
                            Button(
                                onClick = { isEditing = !isEditing },
                                shape = MaterialTheme.shapes.extraLarge,
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                            ) {
                                Text(text = if (isEditing) "Cancel" else "Edit Profile", color = Color.White)
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
    Column(modifier = Modifier.fillMaxWidth()) {
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
                .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha=0.1f),
                    shape = MaterialTheme.shapes.medium)
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
