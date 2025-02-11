package com.hakudesu.finalcloneapp.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.hakudesu.finalcloneapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleScreen(navController: NavController) {
    val emailState = remember { mutableStateOf("") }
    val isEmailValid = remember { mutableStateOf(true) } // To track if the email is valid

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA)),
        color = Color(0xFFF8F9FA)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Home Icon",
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .clickable { navController.popBackStack() }
                    .width(80.dp)
                    .height(35.dp)
                    .align(AbsoluteAlignment.Left),

                tint = Color.Black
            )
            Spacer(modifier = Modifier.height(50.dp))
            // Google Logo
            Image(
                painter = rememberImagePainter(data = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_92x30dp.png"),
                contentDescription = "Google Logo",
                modifier = Modifier
                    .size(152.dp, 60.dp)
                    .padding(bottom = 16.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))
            // Title
            Text(
                text = "Sign in",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Subtitle
            Text(
                text = "with your Google Account",
                fontSize = 14.sp,
                color = Color(0xFF5F6368),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Email Input Field
            OutlinedTextField(
                value = emailState.value,
                onValueChange = {
                    emailState.value = it
                    isEmailValid.value = it.isNotEmpty() // Update validation state
                },
                placeholder = { Text("Email or phone") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(4.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF1A73E8),
                    unfocusedBorderColor = Color(0xFFDADCE0)
                ),
                isError = !isEmailValid.value // Show error state if email is invalid
            )

            if (!isEmailValid.value) {
                Text(
                    text = "Please enter an email or phone number",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            // Forgot Email Link
            Text(
                text = "Forgot email?",
                color = Color(0xFF1A73E8),
                fontSize = 14.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .clickable { /* Handle forgot email */ }
            )

            // Guest Mode Text
            Text(
                text = "No Account? Use Guest mode to sign in privately.",
                fontSize = 15.sp,
                color = Color(0xFF5F6368),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            // Learn More Link
            Text(
                text = "Learn more",
                color = Color(0xFF1A73E8),
                fontSize = 13.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    .clickable { /* Handle learn more */ }
            )
            Spacer(modifier = Modifier.height(50.dp))
            // Bottom Row (Create Account and Next Button)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Create Account Link
                Text(
                    text = "Create account",
                    color = Color(0xFF1A73E8),
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { /* Handle create account */ }
                )

                // Next Button
                Button(
                    onClick = {
                        if (emailState.value.isNotEmpty()) {
                            // Navigate to the next screen and pass the email value
                            navController.navigate("GooglePasswordScreen/${emailState.value}")
                        } else {
                            isEmailValid.value = false // Show error if email is empty
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A73E8)),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = "Next",
                        color = Color.White,
                        fontSize = 14.sp,
                    )
                }
            }
            Spacer(modifier = Modifier.height(56.dp))
            // Sign in with Gmail Box
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* Handle Gmail sign-in */ }
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(4.dp),
                color = Color.White,
                border = BorderStroke(1.dp, Color(0xFFDADCE0))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    // Gmail Icon
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "Gmail Icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    // Gmail Text
                    Text(
                        text = "Sign in with Gmail",
                        fontSize = 16.sp,
                        color = Color(0xFF5F6368)
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewGoogleSignInScreen() {
    GoogleScreen(navController = rememberNavController())
}