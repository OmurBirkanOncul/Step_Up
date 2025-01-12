package com.example.efe

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SkippedTasksScreen() { // Updated function name
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Skipped Tasks", color = Color.Yellow) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add action */ },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Text("+", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }
    ) { padding ->
        SkippedTaskList(modifier = Modifier.padding(padding))
    }
}

@Composable
fun SkippedTaskList(modifier: Modifier = Modifier) {
    val tasks = listOf(
        "Turn off your phone" to "Take a breath!",
        "Take a break" to "One more step—let’s crush it!",
        "Do a digital file cleanup" to "Strong habits, stronger you!",
        "Assign tasks to members" to "Dream big, act small!",
        "Follow-Up after meetings" to "Your body will thank you tomorrow!",
        "Set focus your project" to "Every day is a fresh start!",
        "Attend Conferences" to "Small steps lead to big changes!"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        tasks.forEach { (title, subtitle) ->
            SkippedTaskCard(title = title, subtitle = subtitle)
        }
    }
}

@Composable
fun SkippedTaskCard(title: String, subtitle: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Add Action */ },
        colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = androidx.compose.material3.CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Task Icon Placeholder
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

            // Action Icon Placeholder
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Yellow, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("-", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 20.sp)
            }
        }
    }
}
