package com.example.stepup.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TimeAndDescScreen(
    navController: NavController,
    taskTitle: String,
    onDescriptionEntered: (String) -> Unit
) {
    var description by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add Description",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.background
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
                // Zaman Seçimi Görseli (Pasif)
                Text(
                    text = "Choose a Time (Visual Only)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TimeSelector(value = "08", onValueChange = {})
                    TimeSelector(value = "30", onValueChange = {})
                    TimeSelector(value = "AM", onValueChange = {})
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Açıklama Alanı
                Text(
                    text = "Description",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = {
                        Text(text = "For example: Meeting at 10AM")
                    },
                    trailingIcon = {
                        if (description.isNotEmpty()) {
                            IconButton(onClick = { description = "" }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear",
                                    tint = Color.Gray
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background,
                            shape = MaterialTheme.shapes.medium)
                )

                Spacer(modifier = Modifier.weight(1f))

                // Devam ve İptal Butonları
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { navController.navigate("home") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF869197)),
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
                            onDescriptionEntered(description)
                            navController.navigate("choose_icon?taskTitle=$taskTitle&description=$description")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF043054)),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                            .height(50.dp)
                    ) {
                        Text(
                            text = "Continue",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun TimeSelector(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        enabled = false, // Pasif bileşen
        modifier = Modifier
            .width(80.dp)
            .height(60.dp)
            .background(Color(0xFF2196F3), shape = MaterialTheme.shapes.medium),
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    )
}
