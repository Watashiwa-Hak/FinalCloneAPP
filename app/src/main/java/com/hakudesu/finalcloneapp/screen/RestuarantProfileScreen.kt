package com.hakudesu.finalcloneapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hakudesu.finalcloneapp.R
import com.hakudesu.finalcloneapp.models.MenuItemData
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp

@Composable
fun RestaurantProfileScreen(
    navController: NavController,
    restaurantName: String,
    rating: String,
    deliveryTime: String,
    cuisine: String,
    imageRes: Int
) {
    var isDarkMode by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("en") }
    val textColor = if (isDarkMode) Color.White else Color.Black
    val bgColor = if (isDarkMode) Color(0xFF121212) else Color(0xFFF4F4F4) // Dark Mode Background

    val translations = mapOf(
        "en" to mapOf(
            "Cuisine" to "Cuisine",
            "DeliveryTime" to "Delivery Time",
            "Rating" to "Rating",
            "Menu" to "Menu",
            "HawaiianPizza" to "Hawaiian Pizza",
            "Price" to "$12.75"
        ),
        "kh" to mapOf(
            "Cuisine" to "ប្រភេទម្ហូប",
            "DeliveryTime" to "ពេលវេលាដឹកជញ្ជូន",
            "Rating" to "ការវាយតម្លៃ",
            "Menu" to "មីនុយ",
            "HawaiianPizza" to "ភីហ្សាហាវ៉ៃ",
            "Price" to "១២.៧៥$"
        )
    )

    val currentTranslation = translations[selectedLanguage]!!

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .background(color = bgColor), // Dark mode applied
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColor)
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFFFF8000), Color(0xFFFF5500))
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(top = 80.dp)
                                .size(100.dp)
                                .clip(CircleShape)
                                .background(Color.White) // White background around image
                                .border(3.dp, Color.White, CircleShape), // White border for emphasis
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = imageRes),
                                contentDescription = "Restaurant Logo",
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(CircleShape), // Circular logo

                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        // White Box for Name Below Logo
                        Box(
                            modifier = Modifier
                                .background(Color.White, shape = RoundedCornerShape(10.dp))
                                .padding(horizontal = 20.dp, vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = restaurantName,
                                color = Color.Black,
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                }
            }
            item {
                RestaurantDetails(
                    cuisine = cuisine,
                    deliveryTime = deliveryTime,
                    rating = rating,
                    textColor = textColor,
                    isDarkMode = isDarkMode,
                    currentTranslation = currentTranslation
                )
            }
            item {
                Text(
                    text = currentTranslation["Menu"]!!,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    ),
                    modifier = Modifier.padding(16.dp)
                )
            }
            items(getMenuItems(currentTranslation)) { item ->
                MenuItemComposable(item = item, textColor = textColor)
            }
        }
    }
}

@Composable
fun RestaurantDetails(
    cuisine: String,
    deliveryTime: String,
    rating: String,
    textColor: Color,
    isDarkMode: Boolean,
    currentTranslation: Map<String, String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(4.dp, RoundedCornerShape(10.dp))
            .background(if (isDarkMode) Color(0xFF1E1E1E) else Color.White, RoundedCornerShape(10.dp))
            .padding(16.dp),


    ) {
        Text(
            text = "🍽️ ${currentTranslation["Cuisine"]!!}: $cuisine",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        )
        Spacer(modifier = Modifier.height(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "🚀 ${currentTranslation["DeliveryTime"]!!}: $deliveryTime",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF4CAF50)
                )
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "⭐ ${currentTranslation["Rating"]!!}: $rating",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFC107)
                )
            )
        }
    }
}

@Composable
fun MenuItemComposable(item: MenuItemData, textColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = textColor
            )
            Text(
                text = item.price,
                style = MaterialTheme.typography.bodySmall,
            color = textColor,

            )
        }
    }
}

fun getMenuItems(translation: Map<String, String>): List<MenuItemData> {
    return listOf(
        MenuItemData(translation["HawaiianPizza"]!!, translation["Price"]!!, R.drawable.pizzafood),
        MenuItemData(translation["HawaiianPizza"]!!, translation["Price"]!!, R.drawable.pizzafood),
        MenuItemData(translation["HawaiianPizza"]!!, translation["Price"]!!, R.drawable.pizzafood),
        MenuItemData(translation["HawaiianPizza"]!!, translation["Price"]!!, R.drawable.pizzafood),
        MenuItemData(translation["HawaiianPizza"]!!, translation["Price"]!!, R.drawable.pizzafood),
        MenuItemData(translation["HawaiianPizza"]!!, translation["Price"]!!, R.drawable.pizzafood),
        MenuItemData(translation["HawaiianPizza"]!!, translation["Price"]!!, R.drawable.pizzafood),
        MenuItemData(translation["HawaiianPizza"]!!, translation["Price"]!!, R.drawable.pizzafood),
        MenuItemData(translation["HawaiianPizza"]!!, translation["Price"]!!, R.drawable.pizzafood),
    )
}

@Preview(showBackground = true, name = "Restaurant Profile Preview")
@Composable
fun RestaurantProfilePreview() {
    val mockRestaurantName = "Pizza Paradise"
    val mockRating = 4.5
    val mockDeliveryTime = "30 min"
    val mockCuisine = "Italian"
    val mockImageRes = R.drawable.pizzafood

    MaterialTheme {
        RestaurantProfileScreen(
            navController = rememberNavController(),
            restaurantName = mockRestaurantName,
            rating = mockRating.toString(),
            deliveryTime = mockDeliveryTime,
            cuisine = mockCuisine,
            imageRes = mockImageRes
        )
    }
}