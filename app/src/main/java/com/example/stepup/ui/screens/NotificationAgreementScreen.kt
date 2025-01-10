package com.example.stepup.ui.screens

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.stepup.R
import com.example.stepup.helpers.PreferencesHelper

/*class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StepUpTheme() {
                val navController = rememberNavController()
                NotificationAgreementScreen(navController = navController)
            }
        }
    }
}*/

@Composable
fun NotificationAgreementScreen(navController: NavHostController) {
    var currentScreen by remember { mutableStateOf("agreement") }
    val context = LocalContext.current
    val preferencesHelper = PreferencesHelper(context)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                preferencesHelper.setNotificationsEnabled(true)
                navController.navigate("home")
            } else {
                //println("İzin reddedildi") // Logcat
            }
        }
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.vector),
            contentDescription = "Notification Icon",
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Turn On Notifications",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "This section allows you to customize your " +
                    "notification preferences to fit your routine. " +
                    "Notifications help you stay consistent with " +
                    "your habits and remind you of important " +
                    "tasks. You can enable or disable them as needed.",
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.POST_NOTIFICATIONS
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        preferencesHelper.setNotificationsEnabled(true)
                        navController.navigate("home")
                    } else {
                        launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                } else {
                    preferencesHelper.setNotificationsEnabled(true)
                    navController.navigate("home")
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Allow notifications")
        }
        Button(
            onClick = { navController.navigate("home") },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Maybe later")
        }

        Spacer(modifier = Modifier.height(80.dp))
    }
}
