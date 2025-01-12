package com.example.stepup.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stepup.R
import com.example.stepup.models.Task

@Composable
fun ChooseIconScreen(
    navController: NavController,
    taskTitle: String,
    description: String,
    onTaskSave: (Task) -> Unit
) {
    var selectedIcon by remember { mutableStateOf(-1) }

    val iconList = listOf(
        R.drawable.android,
        R.drawable.audio,
        R.drawable.bisiklet,
        R.drawable.water
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Choose Icon",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFF9800),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Grid for icons
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(Color(0xFF2196F3), shape = MaterialTheme.shapes.medium)
                        .padding(16.dp)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2), // 2 sütun
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(iconList.size) { index ->
                            Button(
                                onClick = { selectedIcon = iconList[index] },
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(70.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedIcon == iconList[index]) Color.White else Color(0xFF2196F3)
                                )
                            ) {
                                Icon(
                                    painter = painterResource(id = iconList[index]),
                                    contentDescription = "Icon $index",
                                    tint = if (selectedIcon == iconList[index]) Color(0xFF2196F3) else Color.White
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Cancel and Save buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { navController.navigate("home") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722)),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                            .height(50.dp)
                    ) {
                        Text(
                            text = "Cancel",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }

                    Button(
                        onClick = {
                            if (selectedIcon != -1) { // İkon seçildiyse
                                onTaskSave(
                                    Task(
                                        title = taskTitle,
                                        description = description,
                                        iconId = selectedIcon
                                    )
                                )
                                navController.navigate("home") {
                                    popUpTo("home") { inclusive = true }
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                            .height(50.dp)
                    ) {
                        Text(
                            text = "Save",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    )
}

