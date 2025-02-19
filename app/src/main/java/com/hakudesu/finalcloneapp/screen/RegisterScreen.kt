package com.hakudesu.finalcloneapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen(navController = rememberNavController())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    var isDarkMode by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("en") }
    val username = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val systemUiController = rememberSystemUiController()
    val translations = mapOf(
        "en" to mapOf(
            "signUp" to "Sign Up",
            "enterPhone" to "Enter your phone number to get started",
            "enterUsername" to "Enter Username",
            "enterPhoneNumber" to "Enter Phone Number",
            "enterPassword" to "Enter Password",
            "confirmPassword" to "Confirm Password",
            "terms" to "By signing up, you agree to our Terms of Service Policy.",
            "next" to "Next",
            "back" to "Back"
        ),
        "kh" to mapOf(
            "signUp" to "ចុះឈ្មោះ",
            "enterPhone" to "បញ្ចូលលេខទូរស័ព្ទរបស់អ្នកដើម្បីចាប់ផ្តើម",
            "enterUsername" to "បញ្ចូលឈ្មោះអ្នកប្រើ",
            "enterPhoneNumber" to "បញ្ចូលលេខទូរស័ព្ទ",
            "enterPassword" to "បញ្ចូលពាក្យសម្ងាត់",
            "confirmPassword" to "បញ្ជាក់ពាក្យសម្ងាត់",
            "terms" to "ដោយចុះឈ្មោះ អ្នកយល់ព្រមនឹងគោលការណ៍សេវាកម្មរបស់យើង.",
            "next" to "បន្ទាប់",
            "back" to "ថយក្រោយ"
        )
    )

    val currentTranslation = translations[selectedLanguage]!!
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = !isDarkMode
        )
    }
    MaterialTheme(
        colorScheme = if (isDarkMode) MaterialTheme.colorScheme.copy(
            background = Color(0xFF121212),
            onBackground = Color.White,
            surface = Color(0xFF1E1E1E),
            onSurface = Color.White
        ) else MaterialTheme.colorScheme.copy(
            background = Color(0xFFF5F5F5),
            onBackground = Color.Black,
            surface = Color.White,
            onSurface = Color.Black
        )
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
                    contentAlignment = Alignment.Center
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
                                color = Color(0xFFB6720A)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(50.dp))
                Text(currentTranslation["signUp"]!!, fontSize = 42.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(30.dp))
                Text(currentTranslation["enterPhone"]!!, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = username.value,
                    onValueChange = { username.value = it },
                    label = { Text(currentTranslation["enterUsername"]!!, fontSize = 14.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = phoneNumber.value,
                    onValueChange = { phoneNumber.value = it },
                    label = { Text(currentTranslation["enterPhoneNumber"]!!, fontSize = 14.sp) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                )


                Spacer(modifier = Modifier.height(20.dp))
                Text(currentTranslation["terms"]!!, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(100.dp))
                Button(
                    onClick = { navController.navigate("PasswordScreen") },
                    modifier = Modifier.fillMaxWidth().height(55.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(currentTranslation["next"]!!, color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}
