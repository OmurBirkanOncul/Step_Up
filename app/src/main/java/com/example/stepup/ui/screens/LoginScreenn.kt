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
import androidx.compose.material.Button // Material Design butonları için
import androidx.compose.material.ButtonDefaults // Buton varsayılan ayarlarını değiştirmek için
import androidx.compose.material.Icon // İkon eklemek için
import androidx.compose.material.icons.Icons // İkonlar koleksiyonu için
import androidx.compose.material.icons.filled.Email // E-posta simgesi için
import androidx.compose.material3.Text // Text bileşeni için
import androidx.compose.runtime.Composable // Composable fonksiyonları tanımlamak için
import androidx.compose.ui.Alignment // Hizalama için
import androidx.compose.ui.Modifier // Görsel düzenlemeler için
import androidx.compose.ui.graphics.Color // Renkler için
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight // Yazı fontu ağırlığı için
import androidx.compose.ui.text.style.TextAlign // Yazı hizalama için
import androidx.compose.ui.tooling.preview.Preview // Önizleme modu için
import androidx.compose.ui.unit.dp // Piksel birimleriyle ölçüm yapmak için
import androidx.compose.ui.unit.sp // Yazı büyüklüğünü ayarlamak için

@Composable
fun StepUpScreen() { // Kullanıcı arayüzünü oluşturan fonksiyon
    Column( // Elemanları dikey hizalar
        modifier = Modifier
            .fillMaxSize() // Ekranı tamamen doldurur
            .background(Color.DarkGray), // Arkaplan rengini koyu gri yapar
        horizontalAlignment = Alignment.CenterHorizontally, // Elemanları yatayda ortalar
        verticalArrangement = Arrangement.Top // Elemanları dikeyde üste hizalar
    )
    {
        Spacer(modifier = Modifier.height(200.dp))
        // STEPUP Başlığı
        androidx.compose.material3.Icon(
            painter = painterResource(id = R.drawable.StepUp),
            contentDescription = "Contract Icon",
            tint = Color(0xFF03A9F4),
            modifier = Modifier.size(200.dp)
        )


        Spacer(modifier = Modifier.height(10.dp))// Yazıyla buton arasına boşluk ekler

        Button( // Buton bileşeni
            onClick = { /* Yeni Hesap Oluştur */ }, // Tıklama işlemi
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF03A9F4)), // Buton rengi
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
                color = Color(0xFFFFE066) // Sarı renk
            )
            Text(
                text = "Log in", // Giriş yap metni
                color = Color(0xFF2FCC71), // Yeşil renk
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
                color = Color(0xFF2FCC71), // Yeşil renk
                textAlign = TextAlign.Center, // Metni ortalar
                fontSize = 12.sp // Yazı büyüklüğü
            )
            Text(
                text = "Terms of Service & Privacy Policy", // Alt bilgilendirme metni
                color = Color(0xFFFFE066), // Sarı renk
                textAlign = TextAlign.Center, // Metni ortalar
                fontSize = 12.sp // Yazı büyüklüğü
            )
        }
    }
}

class file : ComponentActivity() { // Activity sınıfı
    override fun onCreate(savedInstanceState: Bundle?) { // Aktivite başlatıldığında çalışır
        super.onCreate(savedInstanceState)
        setContent { // Composable fonksiyonu çağırır
            StepUpScreen()
        }
    }
}

@Preview(showBackground = true) // Önizleme modu sağlar
@Composable
fun PreviewStepUpScreen() { // StepUpScreen için önizleme
    StepUpScreen()
}
