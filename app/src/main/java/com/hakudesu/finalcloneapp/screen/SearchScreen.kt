package com.hakudesu.finalcloneapp.screen


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hakudesu.finalcloneapp.R

@Preview(showBackground = true)
@Composable
fun SearchScreenR() {
    SearchScreen(navController = rememberNavController())
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController) {
    var isDarkMode by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("en") }
    val textColor =  Color.Black
    val arrowColor = if (isDarkMode) Color.White else Color.Black
    val backgroundColor = if (isDarkMode) Color(0xFF121212) else Color(0xFFF8F8F8)
    val bgColor = if (isDarkMode) Color(0xFF121212) else Color(0xFFF8F8F8)
    var searchText by remember { mutableStateOf("") }
    val systemUiController = rememberSystemUiController()
    // Translation map
    val translations = mapOf(
        "en" to mapOf(
            "searchPlaceholder" to "What are you craving?",
            "khmerTag" to "Khmer | ខ្មែរ",
            "coffeeTag" to "Coffee | កាហ្វេ",
            "snackTag" to "Snack | ស្នាក់",
            "asianTag" to "Asian | អាស៊ី",
            "spicyNoodles" to "Khmer Spicy Noodles ",
            "bokLahong" to "Bok Lahong Bei Sas",
            "coconutCoffee" to "Lintin Coconut Coffee - លីនតីន កូិបី"
        ),
        "kh" to mapOf(
            "searchPlaceholder" to "តើអ្នកចង់ញ៉ាំអ្វី?",
            "khmerTag" to "Khmer | ខ្មែរ",
            "coffeeTag" to "Coffee | កាហ្វេ",
            "snackTag" to "Snack | ស្នាក់",
            "asianTag" to "Asian | អាស៊ី",
            "spicyNoodles" to "មីហិលខ្មែរ",
            "bokLahong" to "បុកល្ហុងបីសាស",
            "coconutCoffee" to "កាហ្វេដូងលីនតីន - លីនតីន កូិបី"
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
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = {},
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = { navController.popBackStack() }) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = arrowColor
                        )
                    }
                },
                actions = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 8.dp),

                        ) {
                        //Dark Mode /Light Mode
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
                                color = Color(0xFFE88E08)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = bgColor)
            )
        },
        bottomBar = { Footer(navController) },
        modifier = Modifier.background(backgroundColor)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(backgroundColor)
        ) {
            Column(
                modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(16.dp)
            )
                {

                // Search Bar
                BasicTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier
                        .fillMaxWidth()
//                        .padding(8.dp)
                        .background(Color(0xFFE7E7E7), RoundedCornerShape(10.dp))
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    textStyle = LocalTextStyle.current.copy(color = textColor),
                    decorationBox = { innerTextField ->
                        if (searchText.isEmpty()) {
                            Text(
                                text = currentTranslation["searchPlaceholder"] ?: "",
                                color = Color.Gray
                            )
                        }
                        innerTextField()
                    }
                )

                // Search Tags
                LazyRow(
                    modifier = Modifier.padding(vertical = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(listOf("khmerTag", "coffeeTag", "snackTag", "asianTag")) { tag ->
                        Box(
                            modifier = Modifier
                                .background(Color.LightGray, RoundedCornerShape(20.dp))
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .clickable { /* Handle tag click */ }
                        ) {
                            Text(
                                text = currentTranslation[tag] ?: "",
                                color = textColor
                            )
                        }
                    }
                }

                // Food Recommendations
                Column(
//                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    FoodItem(
                        imageRes = R.drawable.food1, // Replace with actual image resource
                        title = currentTranslation["spicyNoodles"] ?: "",
                        distance = "2.7km",
                        textColor = textColor
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    FoodItem(
                        imageRes = R.drawable.food2, // Replace with actual image resource
                        title = currentTranslation["bokLahong"] ?: "",
                        distance = "1.1km",
                        textColor = textColor
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    FoodItem(
                        imageRes = R.drawable.food3, // Replace with actual image resource
                        title = currentTranslation["coconutCoffee"] ?: "",
                        distance = "1.0km",
                        textColor = textColor
                    )
                }
            }
        }
    }
}

@Composable
fun FoodItem(imageRes: Int, title: String, distance: String, textColor: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = distance,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}