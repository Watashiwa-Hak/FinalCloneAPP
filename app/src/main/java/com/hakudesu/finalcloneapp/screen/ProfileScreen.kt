package com.hakudesu.finalcloneapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import com.hakudesu.finalcloneapp.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ProfileScreen(navController: NavController) {
    var isDarkMode by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("en") }
    val textColor = if (isDarkMode) Color.White else Color.Black
    val numColor = Color.Black
    val bgColor = if (isDarkMode) Color(0xFF121212) else Color(0xFFF4F4F4) // Dark Mode Background
    val systemUiController = rememberSystemUiController()
    val translations = mapOf(
        "en" to mapOf(
            "Profile" to "Profile",
            "Posts" to "Posts",
            "Orders" to "Orders",
            "WishList" to "Wish List",
            "Likes" to "Likes",
            "AccountInfo" to "Account Information",
            "MyOrder" to "My Order",
            "PaymentMethod" to "Payment Method",
            "DeliveryAddress" to "Delivery Address",
            "Settings" to "Settings",
            "ContactUs" to "Contact with Us",
            "AboutUs" to "About Us",
            "Logout" to "Log out"
        ),
        "kh" to mapOf(
            "Profile" to "ប្រវត្តិរូប",
            "Posts" to "ប្រកាស",
            "Orders" to "ការកម្មង់",
            "WishList" to "បញ្ជីបំណង",
            "Likes" to "ចូលចិត្ត",
            "AccountInfo" to "ព័ត៌មានគណនី",
            "MyOrder" to "ការកម្មង់របស់ខ្ញុំ",
            "PaymentMethod" to "វិធីសាស្រ្តទូទាត់",
            "DeliveryAddress" to "អាសយដ្ឋានដឹកជញ្ជូន",
            "Settings" to "ការកំណត់",
            "ContactUs" to "ទំនាក់ទំនងជាមួយយើង",
            "AboutUs" to "អំពីយើង",
            "Logout" to "ចាកចេញ"
        )
    )

    val currentTranslation = translations[selectedLanguage]!!
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = !isDarkMode
        )
    }
    Scaffold(

                bottomBar = { Footer(navController) }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColor) // Apply dark mode background
                .padding(paddingValues)
        ) {
            // Header Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFFFF8000), Color(0xFFFF5500))
                        ))
                            .padding(20.dp)
                     ){

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically,

                                ) {
                                // Dark Mode Button (Sun/Moon)
                                androidx.compose.material3.IconButton(onClick = {
                                    isDarkMode = !isDarkMode
                                }) {
                                    Text(
                                        text = if (isDarkMode) "☀️" else "🌙",
                                        fontSize = 14.sp,
                                        color = Color.White
                                    )
                                }

                                // Language Change Button (EN/KH)
                                androidx.compose.material3.IconButton(
                                    onClick = {selectedLanguage = if (selectedLanguage == "en") "kh" else "en" },
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(Color(0xFFFFFFF), shape = RoundedCornerShape(20.dp))
                                ) {
                                    Text(
                                        text = if (selectedLanguage == "en") "KH" else "EN",
                                        fontSize = 14.sp,
                                        color = Color.White
                                    )
                                }
                            }
                            Image(
                                painter = painterResource(id = R.drawable.logo), // Replace `logo` with your image name
                                contentDescription = "Logo",
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(CircleShape)
                                    .border(3.dp, Color.White, CircleShape)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Thai Hak",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "@hakudesu",
                                color = Color.White,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White, RoundedCornerShape(10.dp))
                                    .padding(15.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                StatItem(count = "2", label = currentTranslation["Posts"]!!, textColor = numColor)
                                StatItem(count = "6", label = currentTranslation["Orders"]!!, textColor = numColor)
                                StatItem(count = "5", label = currentTranslation["WishList"]!!, textColor = numColor)
                                StatItem(count = "248", label = currentTranslation["Likes"]!!, textColor = numColor)
                            }
                        }
                    }

                        // Menu Items Section
                        Spacer(modifier = Modifier.height(20.dp))
                        MenuSection(
                        items = listOf(
                            MenuItem(icon = "👤", label = currentTranslation["AccountInfo"]!!, color = Color(0xFFFF8000),onClick = {}),
                            MenuItem(icon = "🛒", label = currentTranslation["MyOrder"]!!, color = Color(0xFF28A745),onClick = {}),
                            MenuItem(icon = "💳", label = currentTranslation["PaymentMethod"]!!, color = Color(0xFFFFC107),onClick = {}),
                            MenuItem(icon = "📍", label = currentTranslation["DeliveryAddress"]!!, color = Color(0xFF6F42C1),onClick = {})
                        ),
                textColor = textColor,
                        isDarkMode = isDarkMode
            )

            Spacer(modifier = Modifier.height(20.dp))
            MenuSection(
                items = listOf(
                    MenuItem(icon = "⚙️", label = currentTranslation["Settings"]!!, color = Color(0xFF6C757D),onClick = {}),
                    MenuItem(icon = "💬", label = currentTranslation["ContactUs"]!!, color = Color(0xFF007BFF),onClick = {}),
                    MenuItem(icon = "ℹ️", label = currentTranslation["AboutUs"]!!, color = Color(0xFF6C757D),onClick = { navController.navigate("AboutScreen")}),
                    MenuItem(
                        icon = "🚪",
                        label = currentTranslation["Logout"]!!,
                        color = Color(0xFFD9534F),
                        onClick = { navController.navigate("LoginScreen") }
                    )

                ),
                textColor = textColor,isDarkMode = isDarkMode
            )
        }
    }
}

@Composable
fun StatItem(count: String, label: String, textColor: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = textColor
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun MenuSection(items: List<MenuItem>, textColor: Color, isDarkMode: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isDarkMode) Color(0xFF1E1E1E) else Color.White, RoundedCornerShape(10.dp))
            .padding(15.dp)
    ) {
        items.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { item.onClick() }
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(item.color, CircleShape)
                        .padding(5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = item.icon)
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = item.label,
                    fontSize = 16.sp,
                    color = textColor
                )
            }
        }
    }
}

data class MenuItem(val icon: String, val label: String, val color: Color, val onClick: () -> Unit = {})


@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen(navController = rememberNavController())
}