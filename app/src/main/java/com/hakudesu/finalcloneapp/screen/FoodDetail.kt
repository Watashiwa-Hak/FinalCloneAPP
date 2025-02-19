package com.hakudesu.finalcloneapp.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hakudesu.finalcloneapp.R


@Composable
fun FoodDetailsScreen(navController: NavController) {
    var isDarkMode by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("en") }
    val textColor = if (isDarkMode) Color.White else Color.Black
    val bgColor = if (isDarkMode) Color(0xFF121212) else Color(0xFFF8F8F8)

    val translations = mapOf(
        "en" to mapOf(
            "FoodDetails" to "Food Details",
            "AddToCart" to "Add to Cart",
            "Recommended" to "Recommended",
            "DeliveryInfo" to "📍 123 Food Street, Phnom Penh\n🕒 30 min delivery"
        ),
        "kh" to mapOf(
            "FoodDetails" to "ព័ត៌មានម្ហូប",
            "AddToCart" to "បញ្ចូលទៅកន្ត្រក",
            "Recommended" to "អាហារណែនាំ",
            "DeliveryInfo" to "📍 ១២៣ ផ្លូវម្ហូប, ភ្នំពេញ\n🕒 ដឹកជញ្ជូន ៣០ នាទី"
        )
    )

    val currentTranslation = translations[selectedLanguage]!!

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .background(color = bgColor),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .border(2.dp, color = Color(0xFFFF5722), RoundedCornerShape(50.dp))
                        .padding(horizontal = 28.dp, vertical = 5.dp)
                ) {
                    Button(
                        onClick = { isDarkMode = !isDarkMode },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF8563)
                        ),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.size(width = 80.dp, height = 32.dp)
                    ) {
                        Text(text = if (isDarkMode) "☀️" else "🌙", fontSize = 14.sp)
                    }

                    Button(
                        onClick = { selectedLanguage = if (selectedLanguage == "en") "kh" else "en" },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF8C5EDE)
                        ),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.size(width = 80.dp, height = 32.dp)
                    ) {
                        Text(
                            text = if (selectedLanguage == "en") "KH" else "EN",
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }
                }
            }
        },
        bottomBar = { Footer(navController) }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColor)
                .padding(paddingValues)

        ) {
            // Back button
            Spacer(modifier = Modifier.height(10.dp))
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .clickable { navController.popBackStack() }
                    .width(40.dp)
                    .height(35.dp),
                tint = textColor
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Title
            Text(
                text = currentTranslation["FoodDetails"]!!,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp, start = 10.dp),
                color = textColor
            )

            // Food Card
            FoodCard(
                imageRes = R.drawable.pizzafood,
                name = "Hawaiian Pizza",
                price = "$12.75",
                description = "Pizza with ham and pineapple",
                restaurantInfo = currentTranslation["DeliveryInfo"]!!,
                textColor = textColor,
                bgColor = bgColor
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Recommended Section
            Text(
                text = currentTranslation["Recommended"]!!,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp, start = 10.dp),
                color = textColor
            )

            // Recommended Food Items
            RecommendedFoodItem(
                imageRes = R.drawable.burgerfood,
                name = "Cheeseburger",
                price = "$8.50",
                description = "Classic beef cheeseburger",
                textColor = textColor,
                bgColor = bgColor
            )
            RecommendedFoodItem(
                imageRes = R.drawable.sushifood,
                name = "Sushi Platter",
                price = "$15.00",
                description = "Assorted sushi rolls",
                textColor = textColor,
                bgColor = bgColor
            )
            RecommendedFoodItem(
                imageRes = R.drawable.steakhousefood,
                name = "Tomahawk Steak",
                price = "$10.25",
                description = "Juicy grilled steak",
                textColor = textColor,
                bgColor = bgColor
            )
        }
    }
}

@Composable
fun FoodCard(
    imageRes: Int,
    name: String,
    price: String,
    description: String,
    restaurantInfo: String,
    textColor: Color,
    bgColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
            Text(
                text = price,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF5733)
            )
            Text(
                text = description,
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = restaurantInfo,
                fontSize = 14.sp,
                color = textColor,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /* Handle add to cart */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5733))
            ) {
                Text(text = "Add to Cart", color = Color.White)
            }
        }
    }
}

@Composable
fun RecommendedFoodItem(
    imageRes: Int,
    name: String,
    price: String,
    description: String,
    textColor: Color,
    bgColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(bgColor, RoundedCornerShape(15.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = name,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
            Text(
                text = price,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF5733)
            )
            Text(
                text = description,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FoodDetailsScreen(navController = rememberNavController())
}