package com.hakudesu.finalcloneapp.screen

import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hakudesu.finalcloneapp.R
import androidx.compose.foundation.Image

import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown


import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentMethodScreen(navController: NavController) {
    // State for dark mode and language
    val isDarkMode = remember { mutableStateOf(false) }
    val isKhmerMode = remember { mutableStateOf(false) }
    val systemUiController = rememberSystemUiController()
    // Colors based on dark mode
    val bgColor = if (isDarkMode.value) Color(0xFF121212) else Color(0xFFF8F8F8)
    val textColor = if (isDarkMode.value) Color.White else Color.Black

    // Text based on language
    val paymentMethodText = if (isKhmerMode.value) "វិធីសាស្រ្តទូទាត់" else "Payment Method"
    val cardNumberText = if (isKhmerMode.value) "លេខកាត" else "Card Number"
    val ccvText = if (isKhmerMode.value) "លេខ CCV" else "CCV"
    val addPaymentText = if (isKhmerMode.value) "បន្ថែមការទូទាត់ថ្មី" else "Add New Payment"
    val onlinePaymentText = if (isKhmerMode.value) "ទូទាត់អនឡាញ" else "Online Payment"
    val onDeliveryText = if (isKhmerMode.value) "ទូទាត់ពេលដឹកជញ្ជូន" else "On Delivery"

    // State for payment method selection
    var selectedPaymentMethod by remember { mutableStateOf("") }
    var selectedCard by remember { mutableStateOf("Select Card") }
    var cardNumber by remember { mutableStateOf(TextFieldValue("")) }
    var ccv by remember { mutableStateOf(TextFieldValue("")) }
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = !isDarkMode.value
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(paymentMethodText, color = textColor) },
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
                        // Dark Mode Button
                        IconButton(onClick = { isDarkMode.value = !isDarkMode.value }) {
                            Text(
                                text = if (isDarkMode.value) "☀️" else "🌙",
                                fontSize = 14.sp,
                                color = textColor
                            )
                        }

                        // Language Change Button
                        IconButton(
                            onClick = { isKhmerMode.value = !isKhmerMode.value },
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color.White, shape = RoundedCornerShape(20.dp))
                        ) {
                            Text(
                                text = if (isKhmerMode.value) "KH" else "EN",
                                fontSize = 14.sp,
                                color = textColor
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = bgColor)
            )
        },
        backgroundColor = bgColor
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                // Payment Method Selection
                Text(
                    text = if (isKhmerMode.value) "ជ្រើសរើសវិធីសាស្រ្តទូទាត់" else "Choose Payment Method",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Online Payment Option
                PaymentOption(
                    text = onlinePaymentText,
                    isSelected = selectedPaymentMethod == "Online",
                    onClick = { selectedPaymentMethod = "Online" }
                )

                Spacer(modifier = Modifier.height(10.dp))

                // On Delivery Payment Option
                PaymentOption(
                    text = onDeliveryText,
                    isSelected = selectedPaymentMethod == "OnDelivery",
                    onClick = { selectedPaymentMethod = "OnDelivery" }
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Card Selection and Details
                if (selectedPaymentMethod == "Online") {
                    CardOption("MasterCard", R.drawable.mastercard)
                    Spacer(modifier = Modifier.height(10.dp))
                    CardOption("Visa", R.drawable.visa)

                    Spacer(modifier = Modifier.height(20.dp))

                    DropdownMenu(
                        options = listOf("Select Card", "MasterCard", "Visa"),
                        selectedOption = selectedCard,
                        onOptionSelected = { selectedCard = it }
                    )

                    OutlinedTextField(
                        value = cardNumber,
                        onValueChange = { cardNumber = it },
                        placeholder = { Text(cardNumberText) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = ccv,
                        onValueChange = { ccv = it },
                        placeholder = { Text(ccvText) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                }

                // Add Payment Button
                Button(
                    onClick = { navController.navigate("HomeScreen") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500))
                ) {
                    Text(addPaymentText, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun PaymentOption(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFCCCCCC), RoundedCornerShape(5.dp))
            .background(if (isSelected) Color(0xFFFFA500) else Color(0xFFF1F1F1))
            .clickable { onClick() }
            .padding(10.dp)
    ) {
        Text(text = text, color = if (isSelected) Color.White else Color.Black)
    }
}

@Composable
fun CardOption(cardName: String, iconRes: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFCCCCCC), RoundedCornerShape(5.dp))
            .background(Color(0xFFF1F1F1))
            .padding(10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = cardName,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = cardName, color = Color.Black)
        }
    }
}

@Composable
fun DropdownMenu(options: List<String>, selectedOption: String, onOptionSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = { },
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Dropdown",
                    modifier = Modifier.clickable { expanded = true }
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}