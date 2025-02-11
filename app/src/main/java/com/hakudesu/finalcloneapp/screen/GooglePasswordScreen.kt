package com.hakudesu.finalcloneapp.screen
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
fun GooglePasswordScreen(navController: NavController, email: String) {
    val passwordState = remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA)),
        color = Color(0xFFF8F9FA)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Make the screen scrollable
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Back Button
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
                    .padding(bottom = 16.dp) // Add spacing below the logo
            )

            Spacer(modifier = Modifier.height(50.dp))
            Image(
                painter = rememberImagePainter(data = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_92x30dp.png"),
                contentDescription = "Google Logo",
                modifier = Modifier
                    .size(152.dp, 60.dp)
                    .padding(bottom = 16.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))
            // Email Header
            Text(
                text = email,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5F6368),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Password Input Field
            OutlinedTextField(
                value = passwordState.value,
                onValueChange = { passwordState.value = it },
                placeholder = { Text("Enter your password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(), // Hide password text
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp), // Add spacing below the input field
                shape = RoundedCornerShape(4.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF1A73E8),
                    unfocusedBorderColor = Color(0xFFDADCE0)
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Forgot Password Link
            Text(
                text = "Forgot password?",
                color = Color(0xFF1A73E8),
                fontSize = 14.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp) // Add spacing below the link
                    .clickable { /* Handle forgot password */ }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Guest Mode Text
            Text(
                text = "Not your account? Use Guest mode to sign in privately.",
                fontSize = 15.sp,
                color = Color(0xFF5F6368),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp) // Add spacing below the text
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Learn More Link
            Text(
                text = "Learn more",
                color = Color(0xFF1A73E8),
                fontSize = 13.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp) // Add spacing below the link
                    .clickable { /* Handle learn more */ }
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Next Button
            Button(
                onClick = { navController.navigate("RegisterScreen") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A73E8)),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = "Next",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun PreviewGooglePasswordScreen() {
    GooglePasswordScreen(navController = rememberNavController(), email = "example@gmail.com")
}