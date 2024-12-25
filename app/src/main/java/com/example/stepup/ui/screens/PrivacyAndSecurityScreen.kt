package com.example.stepup.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.stepup.R
import com.example.stepup.ui.components.Sidebar
import kotlinx.coroutines.launch

@Composable
fun PrivacyAndSecurityScreen(navController: NavHostController, onThemeToggle: (Boolean) -> Unit, isDarkTheme: Boolean) {
    val currentScreen by remember { mutableStateOf("privacy") } // Seçili ekranı takip eden değişken
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    //Sidebar
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Sidebar(
                onThemeToggle = onThemeToggle,
                isDarkTheme = isDarkTheme,
                navController = navController,
                currentScreen = "privacy"
            )
        }
    ) {
        Scaffold(
            //Sidebar
            topBar = {
                TopAppBar(
                    title = { Text("") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
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
                        // Başlık
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.privacy),
                                contentDescription = "Privacy Icon",
                                tint = MaterialTheme.colorScheme.tertiary,
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Terms Of Use",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // Metin
                        Text(
                            text = "StepUp values your privacy and is committed to protecting your personal information. All user data is securely encrypted and stored, ensuring it is protected from unauthorized access. We collect and use data only to improve the app’s functionality and provide a better user experience, as outlined in our Privacy Policy. You can manage your privacy preferences through the app’s settings. By using StepUp, you agree to our secure handling of your data.",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                }
            }
        )
    }
}