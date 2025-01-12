package com.example.stepup.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stepup.R

@Composable
fun ContractScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(150.dp))

            // Başlık ve ikon
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = R.drawable.vectorr),
                    contentDescription = "Contract Icon",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Let's Make A Contract",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            // Liste öğeleri
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                TaskItem(
                    iconId = R.drawable.makelist,
                    iconTint = MaterialTheme.colorScheme.tertiary,
                    taskText = "Plan tasks."
                )
                TaskItem(
                    iconId = R.drawable.rotate,
                    iconTint = MaterialTheme.colorScheme.tertiary,
                    taskText = "Set goals."
                )
                TaskItem(
                    iconId = R.drawable.glass,
                    iconTint = MaterialTheme.colorScheme.tertiary,
                    taskText = "Take breaks."
                )
                TaskItem(
                    iconId = R.drawable.people,
                    iconTint = MaterialTheme.colorScheme.tertiary,
                    taskText = "Move and refresh."
                )
                TaskItem(
                    iconId = R.drawable.brain,
                    iconTint = MaterialTheme.colorScheme.tertiary,
                    taskText = "Prioritize."
                )
                TaskItem(
                    iconId = R.drawable.yasak,
                    iconTint = MaterialTheme.colorScheme.tertiary,
                    taskText = "No multitasking."
                )
                TaskItem(
                    iconId = R.drawable.grup,
                    iconTint = MaterialTheme.colorScheme.tertiary,
                    taskText = "Limit social media."
                )
            }

            Spacer(modifier = Modifier.height(150.dp))

            Button(
                onClick = { navController.navigate("agreement") },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "I Agree", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun TaskItem(iconId: Int, iconTint: Color, taskText: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = taskText,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
