package com.example.stepup.ui.screens

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stepup.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun CreateProfileScreen(navController: NavHostController) {
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()

    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var aboutMe by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                imageUri = uri
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Create A Profile!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Profil resmi alanı
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        val intent = Intent(Intent.ACTION_PICK).apply {
                            type = "image/*"
                        }
                        imagePickerLauncher.launch(intent)
                    },
                contentAlignment = Alignment.Center
            ) {
                if (imageUri != null) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Selected Image",
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.Gray, RoundedCornerShape(12.dp))
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.profile), // Varsayılan resim
                        contentDescription = "Default Avatar",
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.Gray, RoundedCornerShape(12.dp))
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            TextFieldSection("What's your name?", "Name", name) { name = it }
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldSection("What's your username?", "Username", username) { username = it }
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldSection(
                "Phone number",
                "Phone Number",
                phoneNumber,
                keyboardType = KeyboardType.Phone
            ) { phoneNumber = it }
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldSection("About me", "About Me", aboutMe) { aboutMe = it }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val userId = auth.currentUser?.uid
                    if (userId != null) {
                        val profileData = mapOf(
                            "name" to name,
                            "username" to username,
                            "phoneNumber" to phoneNumber,
                            "aboutMe" to aboutMe
                        )
                        db.collection("profiles").document(userId)
                            .set(profileData)
                            .addOnSuccessListener {
                                successMessage = "Profile saved successfully!"
                                navController.navigate("create_account")
                            }
                            .addOnFailureListener { e ->
                                errorMessage = e.message ?: "Failed to save profile"
                            }
                    } else {
                        errorMessage = "User not authenticated"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                )
            ) {
                Text("Continue")
            }

            if (errorMessage.isNotEmpty()) {
                Text(errorMessage, color = Color.Red, modifier = Modifier.padding(top = 16.dp))
            }
            if (successMessage.isNotEmpty()) {
                Text(successMessage, color = Color.Green, modifier = Modifier.padding(top = 16.dp))
            }
        }
    }
}

@Composable
fun TextFieldSection(
    label: String,
    placeholder: String,
    value: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )
    }
}
