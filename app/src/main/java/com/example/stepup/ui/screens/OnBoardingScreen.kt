package com.example.stepup.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(navController: NavController) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(innerPadding),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Pager
                HorizontalPager(
                    count = 3,
                    state = pagerState,
                    modifier = Modifier.weight(1f)
                ) { page ->
                    OnboardingPage(page)
                }

                // Pager Indicator
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(3) { index ->
                        val color = if (index == pagerState.currentPage) Color(0xFF2196F3) else Color(0xFFBDBDBD)
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .padding(4.dp)
                                .background(color, shape = CircleShape)
                        )
                    }
                }

                // Skip and Next Buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Skip Button
                    TextButton(
                        onClick = {
                            navController.navigate("start") {
                                popUpTo("onboarding") { inclusive = true }
                            }
                        }
                    ) {
                        Text(
                            text = if (pagerState.currentPage == 2) "Finish" else "Skip",
                            color = Color(0xFF2196F3),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Next Button
                    if (pagerState.currentPage < 2) {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowForward,
                                contentDescription = "Next",
                                tint = Color(0xFF2196F3)
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun OnboardingPage(page: Int) {
    val titles = listOf(
        "Do your tasks quickly\nand easy",
        "Monitor your progress\nconsistently",
        "Stop & break\nbad habits"
    )
    val descriptions = listOf(
        "Your tasks, your rules, our support.",
        "Habits form a fundamental element of our daily lives. Live life to the fullest!",
        "Habits are vital components of our existence. Live life completely!"
    )
    val colors = listOf(
        Color(0xFF4CAF50),
        Color(0xFF2196F3),
        Color(0xFFFF5722)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Text(
            text = "STEP UP",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = colors[page],
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Title
        Text(
            text = titles[page],
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Description
        Text(
            text = descriptions[page],
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Footer Terms and Privacy
        Text(
            text = "By continuing you agree StepUp's\nTerms of Service & Privacy Policy",
            fontSize = 12.sp,
            color = Color(0xFF757575),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}
