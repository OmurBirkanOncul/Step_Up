package com.example.stepup.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.stepup.R
import com.example.stepup.ui.components.BottomNavBar
import com.example.stepup.ui.components.Sidebar
import com.example.stepup.ui.theme.StepUpTheme
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.navigation.compose.rememberNavController

data class Task(
    val title: String,        // Task başlığı
    val description: String,  // Task açıklaması
    val timeLabel: String,    // Zaman etiketi
    val iconId: Int           // İkon ID
)

@Composable
fun SwipeableTaskCard(
    iconId: Int, // Seçilen ikonun drawable ID'si
    title: String,
    description: String,
    timeLabel: String
) {
    var offsetX by remember { mutableStateOf(0f) } // Kartın kaydırma pozisyonu
    val animatedOffsetX by animateFloatAsState(targetValue = offsetX)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 10.dp)
    ) {
        // Arka Butonlar
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(end = 8.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(8.dp)) // Hafif boşluk
            Button(
                onClick = {/*Skip*/ },
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                modifier = Modifier
                    .width(90.dp)
                    .fillMaxHeight()
            ) {
                Text("Complete", color = Color.White)
            }
            Spacer(modifier = Modifier.width(4.dp))
            Button(
                onClick = {/*Skip*/ },
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1BC30)),
                modifier = Modifier
                    .width(90.dp)
                    .fillMaxHeight()
            ) {
                Text("Skip", color = Color.White)
            }
            Spacer(modifier = Modifier.width(4.dp))
            Button(
                onClick = {/*Skip*/ },
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF2C2C)),
                modifier = Modifier
                    .width(90.dp)
                    .fillMaxHeight()
            ) {
                Text("Delete", color = Color.White)
            }
        }

        // Ön Task Kartı
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .offset(x = animatedOffsetX.dp)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { _, dragAmount ->
                            offsetX = (offsetX + dragAmount).coerceIn(-270f, 0f)
                        },
                        onDragEnd = {
                            offsetX = if (offsetX < -135f) -290f else 0f
                        }
                    )
                }
                .zIndex(1f),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF87CEFA))
        ){
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Sol Taraf: İkon
                Image(
                    painter = painterResource(id = iconId), // İkon resource
                    contentDescription = "Task Icon",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 8.dp)
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1664C0)
                    )
                    //Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = description,
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                }

                // Sağ Taraf: Zaman Etiketi
                Box(
                    modifier = Modifier
                        .background(Color(0xFFFFE082), RoundedCornerShape(16.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = timeLabel,
                        color = Color(0xFF8D4B00),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


@Composable
fun HomeScreen(navController: NavHostController, onThemeToggle: (Boolean) -> Unit, isDarkTheme: Boolean) {
    var currentScreen by remember { mutableStateOf("home") } // Seçili ekranı takip eden değişken
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    //var taskList by remember { mutableStateOf(listOf<Task>()) } // Dinamik Task Listesi
    val currentDate = LocalDate.now()
    val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd MMMM, yyyy"))
    val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

    val taskList = listOf(
        Task(
            title = "Complete Homework", description = "Do Math Exercises",
            timeLabel = "30 Min", iconId = R.drawable.skip
        ),
        Task(
            title = "Go for a Run", description = "Run 3 KM",
            timeLabel = "1 Hr", iconId = R.drawable.vector
        ),
        Task(
            title = "Read a Book", description = "Read 20 pages",
            timeLabel = "45 Min", iconId = R.drawable.cancel
        )
    )

//Sidebar
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Sidebar(
                onThemeToggle = onThemeToggle,
                isDarkTheme = isDarkTheme,
                navController = navController,
                currentScreen = currentScreen
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) drawerState.open() else drawerState.close()
                            }
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.sidebar),
                                contentDescription = "Menu Icon",
                                modifier = Modifier.size(24.dp),
                                tint = MaterialTheme.colorScheme.tertiary
                            )
                        }
                    }
                )
            },

            //BottomNavBar
            bottomBar = {
                BottomNavBar(
                    navController = navController,
                    currentScreen = "home",
                    onScreenSelected = { selectedScreen ->
                        currentScreen = selectedScreen
                        navController.navigate(selectedScreen)
                    }
                )
            },

            //Body
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(MaterialTheme.colorScheme.background),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Profil Resmi
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.size(80.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(80.dp)
                                .background(Color.LightGray, CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.calendar),
                            contentDescription = "Calendar Icon",
                            modifier = Modifier
                                .size(32.dp)
                                .padding(end = 8.dp)
                        )
                        // Gün ve Tarih
                        Text(
                            text = formattedDate,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Haftalık Takvim
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        days.forEachIndexed { index, day ->
                            val isToday = index == currentDate.dayOfWeek.value - 1 // Olunan gün kontrolü
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                // Gün İsmi
                                Text(
                                    text = day,
                                    color = Color(0xFF8FD4F8),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                // Tarih Kısmı
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(28.dp)
                                        .background(
                                            color = if (isToday) Color(0xFF1664C0) else Color.Transparent,
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                ) {
                                    Text(
                                        text = "${currentDate.dayOfMonth + index - currentDate.dayOfWeek.value + 1}",
                                        color = if (isToday) Color.White else MaterialTheme.colorScheme.onBackground,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // "My Tasks" Butonu
                    Button(
                        onClick = { /* Navigate to Tasks */ },
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background),
                        modifier = Modifier
                            .width(200.dp)
                            .height(50.dp)
                    ) {
                        Text("My Tasks", color = MaterialTheme.colorScheme.onBackground, fontSize = 20.sp)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    //Task List
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(taskList) { task ->
                            SwipeableTaskCard(
                                iconId = task.iconId,
                                title = task.title,
                                description = task.description,
                                timeLabel = task.timeLabel
                            )
                            Spacer(modifier = Modifier.height(4.dp)) //Card arası boşluk
                        }
                    }
                }
            }
        )
    }
}
