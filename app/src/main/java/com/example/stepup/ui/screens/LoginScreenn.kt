package com.example.stepup.ui.screens

//create new account sayfası(projenin 4.sayfası)

 // Uygulamanın paket adını belirtir

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background // Arkaplan rengi için gerekli kütüphane
import androidx.compose.foundation.clickable // Tıklanabilir alanlar oluşturmak için
import androidx.compose.foundation.layout.* // Layout düzenlemeleri için
import androidx.compose.foundation.shape.RoundedCornerShape // Yuvarlatılmış köşeler için
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import com.example.stepup.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text // Text bileşeni için
import androidx.compose.runtime.Composable // Composable fonksiyonları tanımlamak için
import androidx.compose.ui.Alignment // Hizalama için
import androidx.compose.ui.Modifier // Görsel düzenlemeler için
import androidx.compose.ui.graphics.Color // Renkler için
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight // Yazı fontu ağırlığı için
import androidx.compose.ui.text.style.TextAlign // Yazı hizalama için
import androidx.compose.ui.unit.dp // Piksel birimleriyle ölçüm yapmak için
import androidx.compose.ui.unit.sp // Yazı büyüklüğünü ayarlamak için

/*class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StepUpTheme {
                StepUpScreen()
            }
        }
    }
}*/

@Composable
fun StepUpScreen() { // Kullanıcı arayüzünü oluşturan fonksiyon
    Column( // Elemanları dikey hizalar
        modifier = Modifier
            .fillMaxSize() // Ekranı tamamen doldurur
            .background(MaterialTheme.colorScheme.background), // Arkaplan rengini koyu gri yapar
        horizontalAlignment = Alignment.CenterHorizontally, // Elemanları yatayda ortalar
        verticalArrangement = Arrangement.Top // Elemanları dikeyde üste hizalar
    )
    {
        Spacer(modifier = Modifier.height(200.dp))
        // STEPUP Başlığı
        Icon(
            painter = painterResource(id = R.drawable.step_up),
            contentDescription = "Contract Icon",
            tint = Color(0xFF03A9F4),
            modifier = Modifier.size(200.dp)
        )


        Spacer(modifier = Modifier.height(10.dp))// Yazıyla buton arasına boşluk ekler

        Button( // Buton bileşeni
            onClick = { /* Yeni Hesap Oluştur */ }, // Tıklama işlemi
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary), // Buton rengi
            shape = RoundedCornerShape(16.dp), // Butonun köşelerini yumuşatır
            modifier = Modifier.padding(bottom = 8.dp) // Alt boşluk bırakır
        ) {
            Icon( // Butona ikon ekler
                imageVector = Icons.Default.Email, // E-posta ikonu
                contentDescription = "Email Icon", // İkon açıklaması
                tint = Color.White // İkonun rengini beyaz yapar
            )
            Spacer(modifier = Modifier.width(8.dp)) // İkon ve metin arasında boşluk bırakır
            Text(text = "Create New Account", color = Color.White) // Buton metni
        }

        Row( // Elemanları yatayda hizalar
            modifier = Modifier.padding(bottom = 16.dp) // Alt boşluk
        ) {
            Text(
                text = "Already have an account? ", // Bilgilendirme metni
                color = MaterialTheme.colorScheme.secondary // Sarı renk
            )
            Text(
                text = "Log in", // Giriş yap metni
                color = MaterialTheme.colorScheme.tertiary, // Yeşil renk
                fontWeight = FontWeight.Bold, // Kalın font
                modifier = Modifier.clickable { /* Giriş Yap */ } // Tıklanabilirlik ekler
            )
        }

        Spacer(modifier = Modifier.weight(1f)) // Alt metni alta yaklaştırır

        Column( // Alt bilgilendirme yazısı için
            horizontalAlignment = Alignment.CenterHorizontally, // Yatayda ortalar
            modifier = Modifier.padding(bottom = 100.dp) // En alttan boşluk bırakır
        ) {
            Text(
                text = "By continuing you agree StepUp's", // Bilgilendirme metni
                color = MaterialTheme.colorScheme.tertiary, // Yeşil renk
                textAlign = TextAlign.Center, // Metni ortalar
                fontSize = 12.sp // Yazı büyüklüğü
            )
            Text(
                text = "Terms of Service & Privacy Policy", // Alt bilgilendirme metni
                color = MaterialTheme.colorScheme.secondary, // Sarı renk
                textAlign = TextAlign.Center, // Metni ortalar
                fontSize = 12.sp // Yazı büyüklüğü
            )
        }
    }
}

