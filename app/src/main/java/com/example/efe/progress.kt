package com.example.efe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProgressScreen() { // Updated function name
    var selectedTime by remember { mutableStateOf("Last 7 days") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Progress", color = Color(0xFFFFA500)) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        bottomBar = {
            CustomBottomNavBar()
        },
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
                TextButton(onClick = { /* Open dropdown */ }) {
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
                    text = "You're completed on track. Keep it up!",
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

@Composable
fun CustomBottomNavBar() {
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
