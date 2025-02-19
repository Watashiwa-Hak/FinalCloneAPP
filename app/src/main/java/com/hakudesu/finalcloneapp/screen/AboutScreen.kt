package com.hakudesu.finalcloneapp.screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {
    val isDarkMode = remember { mutableStateOf(false) }
    val isKhmerMode = remember { mutableStateOf(false) } // false = English, true = Khmer
    val textColor = if (isDarkMode.value) Color.White else Color.Black
    val bgColor = if (isDarkMode.value) Color(0xFF121212) else Color(0xFFF8F8F8)
    val systemUiController = rememberSystemUiController()
    // List of team members with their names and image resources
    val teamMembers = listOf(
        TeamMember("Thai Hak", R.drawable.hak),
        TeamMember("Tim ChanChornike", R.drawable.nike),
        TeamMember("Pheng LeabHeng", R.drawable.heng),
        TeamMember("Teng Seangratanak", R.drawable.nak)
    )

    // Randomize positions based on name order
    val positions = listOf("CEO", "CTO", "Designer", "Developer")

    // Translated text
    val missionText = if (isKhmerMode.value) {
        "យើងខ្ញុំមានបំណងផ្តល់នូវផលិតផលដ៏ល្អបំផុតជាមួយនឹងសេវាកម្មគុណភាព និងធ្វើឱ្យការកម្មង់ម្ហូបជាការងាយស្រួល លឿន និងរីករាយសម្រាប់មនុស្សគ្រប់រូប។"
    } else {
        "We aim to provide the best products with quality service and to make food ordering easy, fast, and enjoyable for everyone."
    }

    val visionText = if (isKhmerMode.value) {
        "យើងខ្ញុំមានបំណងផ្តល់នូវផលិតផលដ៏ល្អបំផុតជាមួយនឹងសេវាកម្មគុណភាព និងបទពិសោធន៍ញ៉ាំអាហារដោយគ្មានការលំបាក។"
    } else {
        "We aim to provide the best products with quality service and hassle-free dining experience."
    }

    val teamText = if (isKhmerMode.value) {
        "សូមជួបជាមួយក្រុមការងារដ៏ឧស្សាហ៍ព្យាយាមរបស់យើងខ្ញុំនៅ Haku Delivery។ ពួកគេម្នាក់ៗនាំមកនូវជំនាញ និងបទពិសោធន៍ដ៏ប្លែក។"
    } else {
        "Meet the dedicated individuals who make it all happen at Haku Delivery. Each one brings unique skills and experiences."
    }

    val contactText = if (isKhmerMode.value) {
        "ទំនាក់ទំនងយើងខ្ញុំ"
    } else {
        "Contact Us"
    }

    val emailText = if (isKhmerMode.value) {
        "អ៊ីមែល៖ haku168@gmail.com"
    } else {
        "Email: haku168@gmail.com"
    }

    val phoneText = if (isKhmerMode.value) {
        "ទូរស័ព្ទ៖ 023 456 789"
    } else {
        "Phone: 023 456 789"
    }
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = !isDarkMode.value
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = textColor
                        )
                    }
                },
                actions = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        // Dark Mode Button (Sun/Moon)
                        IconButton(onClick = { isDarkMode.value = !isDarkMode.value }) {
                            Text(
                                text = if (isDarkMode.value) "☀️" else "🌙",
                                fontSize = 14.sp,
                                color = textColor
                            )
                        }

                        // Language Change Button (EN/KH)
                        IconButton(
                            onClick = { isKhmerMode.value = !isKhmerMode.value },
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color(0xFFFFFFF), shape = RoundedCornerShape(20.dp))
                        ) {
                            Text(
                                text = if (isKhmerMode.value) "KH" else "EN",
                                fontSize = 14.sp,
                                color = Color(0xFFF57909)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = bgColor)
            )
        },
        bottomBar = { Footer(navController) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColor)
                .padding(horizontal = 10.dp)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(20.dp) // Even spacing between items
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center), // Centers the Column inside the Box
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo), // Replace with your logo
                            contentDescription = "Splash Logo",
                            modifier = Modifier
                                .size(150.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            "Haku",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFF57909)
                        )
                        Text(
                            if (isKhmerMode.value) "កម្មង់ម្ហូប" else "Food Delivery",
                            fontSize = 18.sp,
                            color = Color(0xFFF57909)
                        )
                    }
                }
            }

            item {
                // Mission Section
                Text(
                    text = if (isKhmerMode.value) "បេសកកម្មរបស់យើង" else "Our Mission",
                    fontSize = 20.sp,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = missionText,
                    fontSize = 16.sp,
                    color = textColor,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                )
            }

            item {
                // Vision Section
                Text(
                    text = if (isKhmerMode.value) "យើងខ្ញុំមានបំណង" else "Our Vision",
                    fontSize = 20.sp,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = visionText,
                    fontSize = 16.sp,
                    color = textColor,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                )
            }

            item {
                // Meet Our Team Section
                Text(
                    text = if (isKhmerMode.value) "ជួបជាមួយក្រុមការងាររបស់យើង" else "Meet Our Team",
                    fontSize = 20.sp,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Full-width Image under "Meet Our Team"
                Image(
                    painter = painterResource(id = R.drawable.team), // Replace with actual image resource
                    contentDescription = "Full Width Image",
                    modifier = Modifier
                        .fillMaxWidth() // Make the image take up full width
                        .height(250.dp) // Adjust the height as needed
                        .clip(RoundedCornerShape(10.dp)) // Optional: Apply rounded corners
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = if (isKhmerMode.value) "ក្រុមការងារដ៏ឧស្សាហ៍ព្យាយាមរបស់យើង" else "Our Dedicated Team",
                    fontSize = 18.sp,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = teamText,
                    fontSize = 16.sp,
                    color = textColor,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                )
            }

            // Display team members
            items(teamMembers.size) { index ->
                TeamMemberCard(
                    name = teamMembers[index].name,
                    imageRes = teamMembers[index].imageRes,
                    position = positions[index],
                    textColor = textColor,
                    bgColor = bgColor
                )
                Spacer(modifier = Modifier.height(2.dp))
            }

            item {


                // Contact Information Section
                Text(
                    text = contactText,
                    fontSize = 20.sp,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = emailText,
                    fontSize = 16.sp,
                    color = textColor,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = phoneText,
                    fontSize = 16.sp,
                    color = textColor,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                )
            }


        }
    }
}

@Composable
fun TeamMemberCard(name: String, imageRes: Int, position: String, textColor: Color, bgColor: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Team member image
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))

            // Team member details
            Column {
                Text(
                    text = name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
                Text(
                    text = position,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

// Data class for team members
data class TeamMember(val name: String, val imageRes: Int)

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen(navController = rememberNavController())
}