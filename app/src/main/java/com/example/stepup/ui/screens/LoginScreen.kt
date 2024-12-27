package com.example.stepup.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                LoginScreen()
            }
        }
    }
}*/

// Geri Butonu (Material 3)
@Composable
fun BackButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .background(Color(0xFF1664C0), shape = RoundedCornerShape(8.dp))
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
    }
}

// Login Ekranı (Material 3)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1E))
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        BackButton(onClick = { /* Handle back navigation */ })

        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = "Log in",
            fontSize = 24.sp,
            color = Color(0xFFFFE066),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // E-posta Alanı
        Text(
            text = "Your Email",
            color = Color(0xFFFFE066),
            fontSize = 16.sp
        )
        var email by remember { mutableStateOf("") }
        OutlinedTextField( // Material 3'te OutlinedTextField kullanılıyor
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Enter your email", color = Color.Gray) },
            shape = RoundedCornerShape(18.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF2FCC71),
                unfocusedBorderColor = Color.Gray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(25.dp))

        // Şifre Alanı
        Text(
            text = "Your Password",
            color = Color(0xFFFFE066),
            fontSize = 16.sp
        )
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Enter your password", color = Color.Gray) },
            shape = RoundedCornerShape(18.dp),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle Password Visibility",
                        tint = Color.Gray
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF2FCC71),
                unfocusedBorderColor = Color.Gray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Forget password?",
            color = Color(0xFF2FCC71),
            modifier = Modifier.align(Alignment.End)
        )

        Spacer(modifier = Modifier.height(100.dp))

        // Giriş Yap Butonu
        Button(
            onClick = { /* Handle login */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1664C0)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = "Log In", color = Color.White)
        }

        Spacer(modifier = Modifier.height(110.dp))

        Text(
            text = "By continuing you agree StepUp’s",
            color = Color(0xFF2FCC71),
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Terms of services & Privacy Policy",
            color = Color(0xFFFFE066),
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
