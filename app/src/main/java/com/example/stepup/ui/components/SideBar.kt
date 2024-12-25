package com.example.stepup.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.stepup.R

@Composable
fun Sidebar(navController: NavHostController, currentScreen: String, onThemeToggle: (Boolean) -> Unit, isDarkTheme: Boolean) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(250.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Menu Bar",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        SidebarItem(
            iconRes = R.drawable.profile,
            text = "Profile",
            onClick = { navController.navigate(route = "profile") },
            isActive = currentScreen == "profile")
        SidebarItem(
            iconRes = R.drawable.about,
            text = "About App",
            onClick = { navController.navigate(route = "about") },
            isActive = currentScreen == "about")
        SidebarItem(
            iconRes = R.drawable.use,
            text = "Terms Of Use",
            onClick = { navController.navigate(route = "terms_of_use") },
            isActive = currentScreen == "terms_of_use")
        SidebarItem(
            iconRes = R.drawable.notification,
            text = "Notifications",
            onClick = { navController.navigate(route = "notifications") },
            isActive = currentScreen == "notifications")
        SidebarItem(
            iconRes = R.drawable.privacy,
            text = "Privacy and Security",
            onClick = { navController.navigate(route = "privacy") },
            isActive = currentScreen == "privacy")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Mode", color = MaterialTheme.colorScheme.onSurface, modifier = Modifier.weight(1f))
            Switch(
                checked = isDarkTheme,
                onCheckedChange = { onThemeToggle(it) }
            )
        }

        SidebarItem(
            iconRes = R.drawable.delete,
            text = "Delete Account",
            textColor = Color(0xFFCC6600),
            iconTint = Color(0xFFCC6600),
            onClick = { navController.navigate(route = "delete_account") }
        )

        Spacer(modifier = Modifier.weight(1f))

        SidebarItem(
            iconRes = R.drawable.logout,
            text = "Logout",
            textColor = Color(0xFFC9A332),
            iconTint = Color(0xFFC9A332),
            onClick = {
            //Navigate to Login
            }
        )
    }
}

@Composable
fun SidebarItem(
    iconRes: Int,
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    iconTint: Color = MaterialTheme.colorScheme.onSurface,
    isActive: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(48.dp)
            .clickable { onClick() }
            .background(if (isActive) Color(0xFF026FC7) else MaterialTheme.colorScheme.surface,
                MaterialTheme.shapes.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier
                .padding(start = 8.dp)
                .size(24.dp),
            colorFilter = ColorFilter.tint(iconTint)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, color = textColor, fontSize = 14.sp)
    }
}