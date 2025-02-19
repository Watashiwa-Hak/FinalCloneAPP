
package com.hakudesu.finalcloneapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hakudesu.finalcloneapp.viewmodels.CartViewModel
import com.hakudesu.finalcloneapp.R
import com.hakudesu.finalcloneapp.models.CartItem
import com.hakudesu.finalcloneapp.screen.Footer
import com.hakudesu.finalcloneapp.screen.HomeScreen

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    val fakeNavController = rememberNavController() // Safe for previews
    CartScreen(navController = fakeNavController)
}
@Composable
fun CartScreen(navController: NavController, viewModel: CartViewModel = viewModel()) {
    var isDarkMode by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("en") }
    val textColor = if (isDarkMode) Color.White else Color.Black
    val bgColor = if (isDarkMode) Color(0xFF121212) else Color(0xFFFFFFFF) // Dark Mode Background

    val translations = mapOf(
        "en" to mapOf(
            "Cart" to "Cart",
            "Price" to "Price",
            "Checkout" to "Checkout",
            "Back" to "Back"
        ),
        "kh" to mapOf(
            "Cart" to "រទេះ",
            "Price" to "តម្លៃ",
            "Checkout" to "ពិនិត្យចេញ",
            "Back" to "ត្រឡប់ក្រោយ"
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
        bottomBar = { Footer(navController) } // Footer will always stick at the bottom
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColor) // Apply dark mode background
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            val cartItems by viewModel.cartItems.collectAsState()
            val totalPrice by viewModel.totalPrice.collectAsState()

            Spacer(modifier = Modifier.height(20.dp))
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = currentTranslation["Back"]
                    )
                }
                Text(
                    text = currentTranslation["Cart"]!!,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))

            // Cart Items List
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(cartItems) { item ->
                    CartItemView(item, onQuantityChange = { newQuantity ->
                        viewModel.updateQuantity(item.id, newQuantity)
                    }, isDarkMode, textColor)
                }
            }

            // Footer
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${currentTranslation["Price"]!!}: $${"%.2f".format(totalPrice)}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
                Button(
                    onClick = {
                        // Pass totalPrice to infoScreen
                        navController.navigate("infoScreen/${totalPrice}")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8C00))
                ) {
                    Text(text = currentTranslation["Checkout"]!!, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun CartItemView(item: CartItem, onQuantityChange: (Int) -> Unit, isDarkMode: Boolean, textColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = if (isDarkMode) Color(0xFF1E1E1E) else Color(0x8183989A))
            .height(80.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(80.dp).padding(8.dp).clip(shape = RoundedCornerShape(18.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.name, fontWeight = FontWeight.Bold, color = textColor)
            Text(text = "$${"%.2f".format(item.price)}", color = Color(0xFFFF8C00))
        }
        Box(
            modifier = Modifier
                .padding(8.dp)
                .border(2.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                .background(if (isDarkMode) Color(0xFF333333) else Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp))
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { onQuantityChange(item.quantity - 1) }) {
                        Text(text = "−", fontSize = 24.sp, color = textColor)
                    }
                    Text(
                        text = item.quantity.toString(),
                        modifier = Modifier.padding(horizontal = 8.dp),
                        color = textColor
                    )
                    IconButton(onClick = { onQuantityChange(item.quantity + 1) }) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Increase",
                            tint = textColor
                        )
                    }
                }
            }
        }
    }
}
