package com.example.stepup.ui.screens

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.stepup.R



class CreateProfile: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateProfileScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProfileScreen() {
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var aboutMe by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    LocalContext.current
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
            .fillMaxSize()
            .background(Color(0xFF2C2A2A)),
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
                color = Color(0xFFFFE066),
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
                         "Selected Image", Modifier
                            .size(100.dp)
                            .background(Color.Transparent, RoundedCornerShape(12.dp)) // Seçilen resmi göster
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.avatar), // Varsayılan resim
                        contentDescription = "Default Avatar",
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.Gray, RoundedCornerShape(12.dp))
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            TextFieldSection("What's your name?", "Name", name) { name = it }
            Spacer(modifier = Modifier.height(15.dp))
            TextFieldSection("What's your username?", "Username", username) { username = it }
            Spacer(modifier = Modifier.height(15.dp))
            TextFieldSection(
                "Phone number",
                "Phone Number",
                phoneNumber,
                keyboardType = KeyboardType.Phone
            ) { phoneNumber = it }
            Spacer(modifier = Modifier.height(15.dp))
            TextFieldSection("About me", "About Me", aboutMe) { aboutMe = it }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Devam et işlemleri */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1664C0),
                    contentColor = Color.White
                )
            ) {
                Text("Continue")
            }
        }
    }
}

fun Image(s: String, background: Modifier) {

}

@Composable
fun TextFieldSection(
    label: String,
    placeholder: String,
    value: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit
) {
    Text(
        text = label,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF1664C0),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, bottom = 4.dp)
    )
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray),
        singleLine = true,
        textStyle = TextStyle(color = Color.White),
        shape = RoundedCornerShape(12.dp)
    )
}
