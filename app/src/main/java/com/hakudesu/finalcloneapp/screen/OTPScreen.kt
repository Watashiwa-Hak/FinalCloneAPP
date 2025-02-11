package com.hakudesu.finalcloneapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
@Preview(showBackground = true)
@Composable
fun PreviewOTPScreen() {
    OTPScreen(navController = rememberNavController())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPScreen(navController: NavController) {
    var otp by remember { mutableStateOf(List(4) { "" }) }
    var timer by remember { mutableStateOf(300) } // 5 minutes in seconds
    var isTimerRunning by remember { mutableStateOf(true) }

    // LaunchedEffect to handle the timer
    LaunchedEffect(isTimerRunning) {
        while (isTimerRunning && timer > 0) {
            delay(1000) // Wait for 1 second
            timer--
        }
    }

    // Format the timer into MM:SS format
    val minutes = timer / 60
    val seconds = timer % 60
    val timerText = String.format("%02d:%02d", minutes, seconds)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Home Icon",
            modifier = Modifier
                .padding(bottom = 8.dp)
                .clickable { navController.popBackStack() }
                .width(40.dp)
                .height(35.dp)
                .align(AbsoluteAlignment.Left),

            tint = Color.Black
        )

        Spacer(modifier = Modifier.height(70.dp))

        Text(
            text = "Verify Phone",
            color = Color.Black,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Enter the OTP sent to +855 880 880",
            color = Color(0xFF333333),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(50.dp))

        Row(
            modifier = Modifier.width(360.dp).padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            otp.forEachIndexed { index, value ->
                OutlinedTextField(
                    value = value,
                    onValueChange = { newValue ->
                        if (newValue.length <= 1) {
                            otp = otp.toMutableList().also { it[index] = newValue }
                        }
                    },
                    modifier = Modifier
                        .width(60.dp)
                        .height(60.dp),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.LightGray
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        LaunchedEffect(timer, isTimerRunning) {
            if (isTimerRunning && timer > 0) {
                delay(1000L) // Wait for 1 second
                timer--  // Decrement timer
            }
        }

        if (timer > 0) {
            Row {
                Text(
                    text = "Resend code in ",
                    color = Color(0xFF333333), // Grey color for "Resend code in"
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = timerText,
                    color = Color(0xFFFF5722), // Orange color for the timer
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            Text(
                text = "Resend code",
                color = Color(0xFFFF5722),
                fontSize = 18.sp,  // Increased size for visibility
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    timer = 30  // Reset timer
                    isTimerRunning = true  // Restart timer
                }
            )
        }


        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { navController.navigate("LoginScreen") },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722))
        ) {
            Text(
                text = "Submit",
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}