package com.example.stepup.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stepup.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.stepup.ui.theme.StepUpTheme

/*class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StepUpTheme() {
                NotificationAgreementScreen( )
            }
        }
    }
}*/

@Composable
fun NotificationAgreementScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.vector),
            contentDescription = "Notification Icon",
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(80.dp)
        )
//        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Turn On Notifications",
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "This section allows you to customize your " +
                    "notification preferences to fit your routine. " +
                    "Notifications help you stay consistent with " +
                    "your habits and remind you of important " +
                    "tasks. You can enable or disable them as needed.",
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { /* Bildirimleri aç */ },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Allow notifications")
        }
        Button(
            onClick = { /* Daha sonra */ },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Maybe later")
        }

        Spacer(modifier = Modifier.height(80.dp))

        // Terms and Privacy Policy
        Text(
            text = "By continuing you agree StepUp’s",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )
        ClickableText(
            text = AnnotatedString("Terms of services & Privacy Policy"),
            style = TextStyle(
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            ),
            onClick = {
                // Tıklanabilir metin işlemi
            }
        )
    }
}
