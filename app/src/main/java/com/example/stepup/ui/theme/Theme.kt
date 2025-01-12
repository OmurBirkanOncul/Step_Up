package com.example.stepup.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Koyu Tema Renk Şeması
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF1664C0), // Mavi
    secondary = Color(0xFF094823), // Yeşil
    tertiary = Color(0xFFF85A12), // turuncu
    primaryContainer = Color(0xFF1664C0),
    background = Color(0xFF1C1F23), // Koyu arka plan
    surface = Color(0xFF1E1E1E), // Kartlar için yüzey
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
    onSurfaceVariant = Color.Black
)

// Açık Tema Renk Şeması
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF2196F3), // Mavi
    secondary = Color(0xFF2B9720), // Yeşil
    tertiary = Color(0xFFFF7F00), //  turuncu
    primaryContainer = Color(0xFF1664C0),
    background = Color(0xFFFFFFFF), // Açık arka plan
    surface = Color(0xFFF2F2F2), // Kartlar için yüzey
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onSurfaceVariant = Color.White
)

@Composable
fun StepUpTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // MaterialTheme içinde renk şeması, tipografi ve içerik kullanılıyor
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
