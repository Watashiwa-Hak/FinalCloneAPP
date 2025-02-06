package com.hakudesu.finalcloneapp.screen

import android.R.attr.top
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.*

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hakudesu.finalcloneapp.R
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

// Define your custom dark color scheme
fun darkColorScheme() = darkColorScheme(
    primary = Color(0xFFBB86FC),
    secondary = Color(0xFF03DAC6),
    tertiary = Color(0xFFF44336),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

// Define your custom light color scheme
fun lightColorScheme() = lightColorScheme(
    primary = Color(0xFFFF5C29),
    secondary = Color(0xFF03DAC6),
    tertiary = Color(0xFFFF9800),
    background = Color(0xFFF5F5F5),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)
@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    MaterialTheme {
        LoginScreen(navController = rememberNavController())
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var emailOrUsername by remember { mutableStateOf(TextFieldValue("")) }
    val password = remember { mutableStateOf("") }
    var isDarkMode by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("en") }

    val translations = mapOf(
        "en" to mapOf(
            "emailOrUsername" to "Email or username",
            "password" to "Type Password",
            "forgetPassword" to "Forget Password",
            "signIn" to "Sign In",
            "orConnectWith" to "Or Connect with",
            "dontHaveAccount" to "Don't have an account?",
            "signUp" to "Sign up"
        ),
        "kh" to mapOf(
            "emailOrUsername" to "អ៊ីមែល ឬឈ្មោះអ្នកប្រើ",
            "password" to "វាយបញ្ចូលពាក្យសម្ងាត់",
            "forgetPassword" to "ភ្លេចពាក្យសម្ងាត់",
            "signIn" to "ចូល",
            "orConnectWith" to "ឬភ្ជាប់ជាមួយ",
            "dontHaveAccount" to "មិនមានគណនីទេ?",
            "signUp" to "ចុះឈ្មោះ"
        )
    )

    val currentTranslation = translations[selectedLanguage]!!

    // Apply the custom color scheme based on the dark mode state
    val customColorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme()

    MaterialTheme(
        colorScheme = customColorScheme
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                // Dark Mode Toggle and Language Selector
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(50.dp))
                            .padding(horizontal = 28.dp, vertical = 5.dp)
                    ) {
                        Button(
                            onClick = { isDarkMode = !isDarkMode },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8563)),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.size(width = 80.dp, height = 32.dp)
                        ) {
                            Text(text = if (isDarkMode) "☀️" else "🌙", fontSize = 14.sp)
                        }

                        Button(
                            onClick = { selectedLanguage = if (selectedLanguage == "en") "kh" else "en" },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8C5EDE)),

                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.size(width = 80.dp, height = 32.dp)
                        ) {
                            Text(text = if (selectedLanguage == "en") "KH" else "EN", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSecondary)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(100.dp))
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Blossom Logo",
                    modifier = Modifier
                        .width(160.dp)
                        .height(90.dp)
                        .padding(bottom = 8.dp)
                )
                Text(
                    "Haku",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    "Food Delivery",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = emailOrUsername,
                    onValueChange = { emailOrUsername = it },
                    label = {
                        Text(
                            currentTranslation["emailOrUsername"]!!,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                    )
                )

                OutlinedTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = {
                        Text(
                            currentTranslation["password"]!!,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 10.dp),
                    shape = RoundedCornerShape(10.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    currentTranslation["forgetPassword"]!!,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(20.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { navController.navigate("HomeScreen") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF653B)),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        currentTranslation["signIn"]!!,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    currentTranslation["orConnectWith"]!!,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(20.dp)
                )
                Row(modifier = Modifier.padding(15.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.apple),
                        contentDescription = "Apple",
                        modifier = Modifier
                            .width(80.dp)
                            .height(40.dp)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Image(
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = "Facebook",
                        modifier = Modifier
                            .width(80.dp)
                            .height(40.dp)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "Google",
                        modifier = Modifier
                            .width(80.dp)
                            .height(40.dp)
                            .clickable { navController.navigate("GoogleScreen") }
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    currentTranslation["dontHaveAccount"]!!,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    currentTranslation["signUp"]!!,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(20.dp)
                        .clickable { navController.navigate("RegisterScreen") }
                )
            }
        }
    }
}




