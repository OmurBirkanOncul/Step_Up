package com.example.stepup.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stepup.ui.theme.StepUpTheme

/*class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StepUpTheme {
                PasswordRecoveryScreen(
                    email = "",
                    onBackClick = { finish() } // Geri tuşuna basınca uygulama kapanır
                )
            }
        }
    }
}*/


@Composable
fun PasswordRecoveryScreen(email: String, onBackClick: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .size(40.dp) // karenin boyutunu ayarı
                            .background(MaterialTheme.colorScheme.primary,
                                shape = MaterialTheme.shapes.medium) // kare
                            .padding(8.dp) // ikonun iç kenar boşluğu
                    ) {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                },
                title = { },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background)
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Relax, we got you covered!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Habits are essential aspect of our lives. Optimize your life to the fullest!",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Using $email to recover",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                Button(
                    onClick = {
                        showDialog = true // Dialog gösterilecek
                    },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors
                            (containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        text = "Send recovery email",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(80.dp))
                Text(
                    text = "By continuing you agree StepUp's",
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
                        // Gizlilik politikasına tıklama işlemi burada yapılabilir
                    }
                )
            }
            // Alert Dialog
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        Button(
                            onClick = {
                                showDialog = false
                                // Login ekranına yönlendirme yapılacak
                            }
                        ) {
                            Text(text = "Back to login!")
                        }
                    },
                    title = {
                        Text(
                            text = "Recovery email sent!",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 18.sp
                        )
                    },
                    text = {
                        Text(
                            text = "Complete the steps in the email and modify your password.",
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    containerColor = MaterialTheme.colorScheme.surface
                )
            }
        }
    )
}
