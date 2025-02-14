package com.hakudesu.finalcloneapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hakudesu.finalcloneapp.R
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun RestaurantScreen(navController: NavController) {
    var isDarkMode by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("en") }
    val textColor = if (isDarkMode) Color.White else Color.Black
    val bgColor = if (isDarkMode) Color(0xFF121212) else Color(0xFFFFFFFF) // Dark Mode Background

    val translations = mapOf(
        "en" to mapOf(

            "Res" to "Restaurants",
            "popRes" to "Popular Restaurants",

        ),
        "kh" to mapOf(

            "Res" to "ភោជនីយដ្ឋាន",
            "popRes" to "ភោជនីយដ្ឋានពេញនិយម",

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
                            backgroundColor = Color(0xFFFF8563)
                        ),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.size(width = 80.dp, height = 32.dp)
                    ) {
                        Text(text = if (isDarkMode) "☀️" else "🌙", fontSize = 14.sp)
                    }

                    Button(
                        onClick = { selectedLanguage = if (selectedLanguage == "en") "kh" else "en" },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF8C5EDE)
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
        bottomBar = { Footer(navController) } // Footer will always stick at the bottom
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColor) // Apply dark mode background
                .padding(paddingValues)
        ) {
            Text(
                text = currentTranslation["Res"]!!,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            RestaurantScroll()
            Spacer(modifier = Modifier.height(20.dp))
            RestaurantList(navController = navController, isDarkMode = isDarkMode, textColor = textColor, translation = currentTranslation)

        }
    }
}
data class Restaurant(
    val imageRes: Int,
    val name: String,
    val rating: String,
    val deliveryTime: String,
    val cuisine: String
)

val restaurantList = listOf(
    Restaurant(R.drawable.pizzacompany, "Pizza Company", " 4.5", " 30-40 min", "Italian, Fast Food"),
    Restaurant(R.drawable.bk, "Burger King", " 4.2", " 25-35 min", "American, Fast Food"),
    Restaurant(R.drawable.sushires, "Sushi Spot", " 4.7", " 40-50 min", "Japanese, Seafood"),
    Restaurant(R.drawable.pastacornerres, "Pasta Corner", " 4.3", " 20-30 min", "Italian, Vegetarian"),
    Restaurant(R.drawable.streakhouseres, "306 Wagyu Steakhouse", " 4.6", " 35-45 min", "Grill, BBQ"),
    Restaurant(R.drawable.pizzacompany, "Pizza Company", " 4.5", " 30-40 min", "Italian, Fast Food"),
    Restaurant(R.drawable.bk, "Burger King", " 4.2", " 25-35 min", "American, Fast Food"),
    Restaurant(R.drawable.sushires, "Sushi Spot", " 4.7", " 40-50 min", "Japanese, Seafood"),
    Restaurant(R.drawable.pastacornerres, "Pasta Corner", " 4.3", " 20-30 min", "Italian, Vegetarian"),
    Restaurant(R.drawable.streakhouseres, "306 Wagyu Steakhouse", " 4.6", " 35-45 min", "Grill, BBQ"),
    Restaurant(R.drawable.pizzacompany, "Pizza Company", " 4.5", " 30-40 min", "Italian, Fast Food"),
    Restaurant(R.drawable.bk, "Burger King", " 4.2", " 25-35 min", "American, Fast Food"),
    Restaurant(R.drawable.sushires, "Sushi Spot", " 4.7", " 40-50 min", "Japanese, Seafood"),
    Restaurant(R.drawable.pastacornerres, "Pasta Corner", " 4.3", " 20-30 min", "Italian, Vegetarian"),
    Restaurant(R.drawable.streakhouseres, "306 Wagyu Steakhouse", " 4.6", " 35-45 min", "Grill, BBQ"),
)
@Composable
fun RestaurantList(
    navController: NavController, // Pass the correct NavController
    isDarkMode: Boolean,
    textColor: Color,
    translation: Map<String, String>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(if (isDarkMode) Color(0xFF1E1E1E) else Color(0xFFF8F8F8)) // Dark mode background
    ) {
        item {
            Text(
                text = translation["popRes"] ?: "Restaurants", // Translated title
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        items(restaurantList) { restaurant ->
            RestaurantCard(navController, restaurant) // Pass the main NavController
        }
    }
}

@Composable
fun RestaurantCard(navController: NavController, restaurant: Restaurant) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("restaurantProfile/${restaurant.name}/${restaurant.rating}/${restaurant.deliveryTime}/${restaurant.cuisine}/${restaurant.imageRes}")
            },
        shape = RoundedCornerShape(10.dp),
        color = Color.White
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = restaurant.imageRes),
                contentDescription = restaurant.name,
                modifier = Modifier.size(64.dp).clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(restaurant.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(restaurant.cuisine, fontSize = 14.sp, color = Color.Gray)
                Text(restaurant.rating, fontSize = 14.sp, color = Color(0xFFFFC107))
                Text(restaurant.deliveryTime, fontSize = 14.sp, color = Color(0xFF4CAF50))
            }
        }
    }
}
