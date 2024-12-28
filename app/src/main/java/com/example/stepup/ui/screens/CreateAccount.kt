package com.example.stepup.ui.screens


// Uygulamanın paket adı

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*class accountcreate : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AccountCreatedScreen() // Burada kendi Composable'ını çağır
            }
        }
    }
}*/

@Composable
fun AccountCreatedScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF303030)) // Dark background
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Checkmark Image
        Image(
            painter = painterResource(id = R.drawable.tikicon), // Replace with your drawable
            contentDescription = "Checkmark Icon",
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Success Message
        Text(
            text = "Your account\nwas successfully created!",
            fontSize = 24.sp,
            color = Color(0xFF2FCC71), // Green color
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Subtitle
        Text(
            text = "Only one click to explore online\neducation.",
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(150.dp)) // Butonun biraz daha aşağıda görünmesi için artırılmış boşluk

        // Log In Button
        Button(
            onClick = { /* TODO: Handle login click */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1664C0) // Blue color
            ),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(48.dp),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                text = "Log In",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(200.dp)) // Alttaki yazıya mesafe için gerekli boşluk

        // Terms of Service and Privacy Policy
        androidx.compose.material.Text(
            text = "By continuing you agree StepUp’s", // Kullanıcı bilgilendirme metni
            color =Color(0xFF2FCC71), // Yeşil renk
            fontSize = 14.sp, // Yazı boyutu
            modifier = Modifier.align(Alignment.CenterHorizontally) // Ortaya hizalar
        )
        androidx.compose.material.Text(
            text = "Terms of services & Privacy Policy", // Kullanıcı bilgilendirme metni
            color =Color(0xFFFFE066), // Sarı renk
            fontSize = 14.sp, // Yazı boyutu
            modifier = Modifier.align(Alignment.CenterHorizontally) // Ortaya hizalar
        )
    }
}