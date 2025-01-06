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
import com.example.stepup.R

class ContractFile : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContractScreen()
        }
    }
}







@Composable
fun ContractScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A))
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
                    tint = Color(0xFF2FCC71),
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Let's Make A Contract",
                    color = Color(0xFF2FCC71),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            // Liste öğeleri
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                TaskItem(
                    iconId = R.drawable.makelist,
                    iconTint = Color(0xFFFFE066),
                    taskText = "Plan tasks."
                )
                TaskItem(
                    iconId = R.drawable.rotate,
                    iconTint = Color(0xFFFFE066),
                    taskText = "Set goals."
                )
                TaskItem(
                    iconId = R.drawable.glass,
                    iconTint = Color(0xFFFFE066),
                    taskText = "Take breaks."
                )
                TaskItem(
                    iconId = R.drawable.people,
                    iconTint = Color(0xFFFFE066),
                    taskText = "Move and refresh."
                )
                TaskItem(
                    iconId = R.drawable.brain,
                    iconTint = Color(0xFFFFE066),
                    taskText = "Prioritize."
                )
                TaskItem(
                    iconId = R.drawable.yasak,
                    iconTint = Color(0xFFFFE066),
                    taskText = "No multitasking."
                )
                TaskItem(
                    iconId = R.drawable.grup,
                    iconTint = Color(0xFFFFE066),
                    taskText = "Limit social media."
                )
            }

            Spacer(modifier = Modifier.height(150.dp))

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1664C0)),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "I Agree", color = Color.White, fontSize = 16.sp)
            }


            Column(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "By continuing you agree StepUp’s",
                    color = Color(0xFF2FCC71),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Terms of services & Privacy Policy",
                    color = Color(0xFFFFE066),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
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
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContractScreenPreview() {
    ContractScreen()
}