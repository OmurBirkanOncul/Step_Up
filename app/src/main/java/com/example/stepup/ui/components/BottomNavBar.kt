package com.example.stepup.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.stepup.R

@Composable
fun BottomNavBar(navController: NavHostController, currentScreen: String, onScreenSelected: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp) // Box'ın yüksekliği
    ) {
        Image(
            painter = painterResource(id = R.drawable.bottombar),
            contentDescription = "Bottom Navigation Background",
            modifier = Modifier.fillMaxSize(), // Görüntü tüm Box'ı kaplar
            contentScale = ContentScale.FillBounds
        )
        Image(
            painter = painterResource(id = R.drawable.ek_bottom),
            contentDescription = "Ek Bottom",
            modifier = Modifier
                .offset(y= 43.dp)
                .fillMaxSize(),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(), // Row, Box'ın tamamını kapsar
            horizontalArrangement = Arrangement.SpaceEvenly, // ikonları eşit aralıklarla hizalar
            verticalAlignment = Alignment.CenterVertically // ikonları ortalar
        ) {
            IconButton(
                onClick = { navController.navigate("home") },
                modifier = Modifier.align(Alignment.Bottom).size(80.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home",
                    modifier = Modifier
                        .size(40.dp), // İkon boyutu
                    tint = if (currentScreen == "home") Color.White else Color.Black
                )
            }
            IconButton(
                onClick = { navController.navigate("statistics") },
                modifier = Modifier.align(Alignment.Bottom).size(80.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.stats),
                    contentDescription = "Statistics",
                    modifier = Modifier
                        .size(80.dp),
                    tint = if (currentScreen == "statistics") Color.White else Color.Black
                )
            }

            Box(
                modifier = Modifier.size(80.dp) // FAB'nin çerçeve boyutu
            ) {
                FloatingActionButton(
                    onClick = { navController.navigate("add_task_title") },
                    containerColor = Color.Transparent, // arka plan rengi şeffaf
                    contentColor = Color.Unspecified,
                    elevation = FloatingActionButtonDefaults.elevation(0.dp), // Gölgeyi kaldır
                    modifier = Modifier
                        .size(80.dp)
                ) {
                    val fabIcon = if (currentScreen == "add") R.drawable.fab_selected else R.drawable.fab
                    Image(
                        painter = painterResource(id = fabIcon),
                        contentDescription = "Add Image",
                        modifier = Modifier.size(80.dp) // görsel boyutu
                    )
                }
            }

            IconButton(
                onClick = { navController.navigate("completed") },
                modifier = Modifier.align(Alignment.Bottom).size(80.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.complete),
                    contentDescription = "Completed",
                    modifier = Modifier
                        .size(80.dp),
                    tint = if (currentScreen == "completed") Color.White else Color.Black
                )
            }
            IconButton(
                onClick = { navController.navigate("skipped") },
                modifier = Modifier.align(Alignment.Bottom).size(80.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.skip),
                    contentDescription = "Skipped",
                    modifier = Modifier
                        .size(34.dp),
                    tint = if (currentScreen == "skipped") Color.White else Color.Black
                )
            }
        }
    }
}