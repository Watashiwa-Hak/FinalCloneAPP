package com.hakudesu.finalcloneapp.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.hakudesu.finalcloneapp.R
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navController = rememberNavController())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var isDarkMode by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("en") }
    val textColor = if (isDarkMode) Color.White else Color.Black
    // Define text color based on dark mode


    val translations = mapOf(
        "en" to mapOf(
            "deliveringTo" to "Delivering To",
            "orderNow" to "Order Now",
            "searchBox" to "Search restaurants and groceries...",
            "categoriesHeader" to "Categories",
            "nearbyHeader" to "Nearby Restaurants",
            "popRes" to "Popular Restaurants",
            "newArrivals" to "New arrivals up to 50% off",
            "topShops" to " Top shops",


        ),
        "kh" to mapOf(
            "deliveringTo" to "កំពុងដឹកជញ្ជូនទៅ",
            "orderNow" to "កម៉្មង់ឥឡូវនេះ",
            "searchBox" to "ស្វែងរកអ្វីៗ...",
            "categoriesHeader" to "ប្រភេទម្ហូប",
            "nearbyHeader" to "ភោជនីយដ្ឋានដែលនៅជិត",
            "popRes" to "ភោជនីយដ្ឋានពេញនិយម",
            "newArrivals" to "មកដល់ថ្មី បញ្ចុះតម្លៃរហូតដល់ 50%",
            "topShops" to "ហាង Top",
        )
    )

    val currentTranslation = translations[selectedLanguage]!!

    MaterialTheme(
        colors = if (isDarkMode) darkColors() else lightColors()
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
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
                            .border(2.dp, androidx.compose.material3.MaterialTheme.colorScheme.primary, RoundedCornerShape(50.dp))
                            .padding(horizontal = 28.dp, vertical = 5.dp)
                    ) {
                        Button(
                            onClick = { isDarkMode = !isDarkMode },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF8563)),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.size(width = 80.dp, height = 32.dp)
                        ) {
                            Text(text = if (isDarkMode) "☀️" else "🌙", fontSize = 14.sp)
                        }

                        Button(
                            onClick = { selectedLanguage = if (selectedLanguage == "en") "kh" else "en" },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8C5EDE)),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.size(width = 80.dp, height = 32.dp)
                        ) {
                            Text(text = if (selectedLanguage == "en") "KH" else "EN", fontSize = 14.sp, color = Color(0xFF673AB7))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Delivering To and Hotel Name
                Text(
                    text = currentTranslation["deliveringTo"]!!,
                    color = Color(0xFF_FF5722), // Keep this color fixed (e.g., for branding)
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Caspian Sport Hotel",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor, // Use dynamic text color
                        modifier = Modifier
                            .padding(8.dp)
                    )
                    IconButton(
                        onClick = { /* Edit action */ },
                        modifier = Modifier
                            .background(Color.LightGray, CircleShape)
                            .padding(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_edit),
                            contentDescription = "Edit"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Search Box
                TextField(
                    value = "",
                    onValueChange = { /* Handle search input */ },
                    placeholder = { Text(currentTranslation["searchBox"]!!, color = Color.Gray) },
                    textStyle = TextStyle(color = textColor), // Use dynamic text color
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(20.dp))
                        .background(Color.White, RoundedCornerShape(20.dp))
                        .clip(RoundedCornerShape(20.dp))
                )

                Spacer(modifier = Modifier.height(30.dp))

                // Carousel
                Carousel()

                Spacer(modifier = Modifier.height(20.dp))

                // Order Now Button
                Button(
                    onClick = { /* Handle order now action */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF_FF5722)),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 4.dp,
                        pressedElevation = 8.dp
                    )
                ) {
                    Text(text = currentTranslation["orderNow"]!!, color = Color.White, fontSize = 17.sp)
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Categories Section
                Text(
                    text = currentTranslation["categoriesHeader"]!!,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor, // Use dynamic text color
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                CategoriesSection(isDarkMode = isDarkMode)

                Spacer(modifier = Modifier.height(10.dp))
                Spacer(modifier = Modifier.height(20.dp))
                // Top shops Section
                Text(
                    text = currentTranslation["topShops"]!!,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor, // Use dynamic text color
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                TopShopsSection(isDarkMode = isDarkMode)



                // Nearby Restaurants Section
                Text(
                    text = currentTranslation["nearbyHeader"]!!,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor, // Use dynamic text color
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(Color.LightGray) // Fallback background color
                ) {
                    // Background Image
                    Image(
                        painter = painterResource(id = R.drawable.map), // Replace with your drawable resource
                        contentDescription = "Background Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(0.5f), // Adjust opacity if needed
                        contentScale = ContentScale.Crop // Adjust the scaling of the image
                    )

                    // Text Overlay
                    Text(
                        text = "Google Map will be here",
                        modifier = Modifier.align(Alignment.Center),
                        color = textColor, // Use dynamic text color
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                // Popular Restaurants Section
                Text(
                    text = currentTranslation["popRes"]!!,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor, // Use dynamic text color
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                RestaurantScroll()
                Spacer(modifier = Modifier.height(20.dp))
                // New arrivals Section
                Text(
                    text = currentTranslation["newArrivals"]!!,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor, // Use dynamic text color
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                NewArrivalScroll()


            }
        }
    }
}

@Composable
fun Carousel() {
    val images = listOf(
        R.drawable.hamburger,
        R.drawable.hamburger,
        R.drawable.hamburger,
    )
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // HorizontalPager for images
        HorizontalPager(
            count = images.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        ) { page ->
            Image(
                painter = rememberImagePainter(data = images[page]),
                contentDescription = "Sale Banner",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp))
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Custom Pagination Indicators
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(images.size) { index ->
                val isActive = index == pagerState.currentPage
                val color = if (isActive) Color(0xFF, 0x00, 0xFF) // RGB for active (purple)
                else Color(0xCC, 0xCC, 0xCC) // RGB for inactive (light gray)

                Box(
                    modifier = Modifier
                        .width(if (isActive) 32.dp else 12.dp) // Larger size for active dot
                        .height(12.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(color)
                        .clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                )
            }
        }
    }
}

@Composable
fun CategoriesSection(isDarkMode: Boolean) {
    val categories = listOf(
        Category(R.drawable.hamburger, "Burger"),
        Category(R.drawable.sushi, "Sushi"),
        Category(R.drawable.pizza, "Pizza"),
        Category(R.drawable.dessert, "Dessert"),
        Category(R.drawable.hamburger, "Burger"),
        Category(R.drawable.sushi, "Sushi"),
        Category(R.drawable.pizza, "Pizza"),
        Category(R.drawable.dessert, "Dessert")
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(categories.size) { index ->
            CategoryItem(category = categories[index], textColor = if (isDarkMode) Color.White else Color.Black)
        }
    }
}

@Composable
fun CategoryItem(category: Category, textColor: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(15.dp)
    ) {
        Image(
            painter = painterResource(id = category.imageResId),
            contentDescription = category.name,
            modifier = Modifier
                .size(55.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = category.name, fontSize = 17.sp, fontWeight = FontWeight.Bold, color = textColor, modifier = Modifier.padding(vertical = 10.dp))
    }
}

data class Category(val imageResId: Int, val name: String)


@Composable
fun RestaurantScroll() {


    // Restaurant Cards Horizontal Scroll
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        RestaurantCard(
            imagePainter = painterResource(id = R.drawable.pizzacompany),
            name = "Pizza Company",
            cuisine = "Italian, Fast Food",
            rating = "⭐ 4.5"
        )
        RestaurantCard(
            imagePainter = painterResource(id = R.drawable.bk),
            name = "Burger King",
            cuisine = "American, Fast Food",
            rating = "⭐ 4.2"
        )
        RestaurantCard(
            imagePainter = painterResource(id = R.drawable.sushires),
            name = "Sushi Spot",
            cuisine = "Japanese, Seafood",
            rating = "⭐ 4.7"
        )
        RestaurantCard(
            imagePainter = painterResource(id = R.drawable.pastacornerres),
            name = "Pasta Corner",
            cuisine = "Italian, Vegetarian",
            rating = "⭐ 4.3"
        )
        RestaurantCard(
            imagePainter = painterResource(id = R.drawable.streakhouseres),
            name = "306 Wagyu Steakhouse",
            cuisine = "Grill, BBQ",
            rating = "⭐ 4.6"
        )
    }
}

@Composable
fun RestaurantCard(imagePainter: Painter, name: String, cuisine: String, rating: String) {
    Column(
        modifier = Modifier
            .width(180.dp)
            .shadow(4.dp, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = imagePainter,
            contentDescription = name,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = cuisine,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Text(
            text = rating,
            fontSize = 14.sp,
            color = Color(0xFFFF9800),
            fontWeight = FontWeight.Bold
        )
    }
}
@Composable
fun NewArrivalScroll() {
    // New Arrival Cards Horizontal Scroll
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        NewArrivalCard(
            imagePainter = painterResource(id = R.drawable.pizzafood),
            name = "Pizza Company",
            category = "Italian, Fast Food",
            discount = "Up to 50% OFF",
            deliveryDistance = "2.5 km",
            deliveryPrice = "$1.99"
        )
        NewArrivalCard(
            imagePainter = painterResource(id = R.drawable.pizzafood),
            name = "Burger King",
            category = "American, Fast Food",
            discount = "Up to 40% OFF",
            deliveryDistance = "3.0 km",
            deliveryPrice = "$2.99"
        )
        NewArrivalCard(
            imagePainter = painterResource(id = R.drawable.pizzafood),
            name = "Sushi Spot",
            category = "Japanese, Seafood",
            discount = "Up to 30% OFF",
            deliveryDistance = "1.8 km",
            deliveryPrice = "$6.99"
        )
        NewArrivalCard(
            imagePainter = painterResource(id = R.drawable.pizzafood),
            name = "Pasta Delight",
            category = "Italian, Vegetarian",
            discount = "Up to 35% OFF",
            deliveryDistance = "2.2 km",
            deliveryPrice = "$1.99"
        )
        NewArrivalCard(
            imagePainter = painterResource(id = R.drawable.pizzafood),
            name = "Steak House",
            category = "Grill, BBQ",
            discount = "Up to 25% OFF",
            deliveryDistance = "4.0 km",
            deliveryPrice = "$2.99"
        )
    }
}

@Composable
fun NewArrivalCard(
    imagePainter: Painter,
    name: String,
    category: String,
    discount: String,
    deliveryDistance: String,
    deliveryPrice: String
) {
    Column(
        modifier = Modifier
            .width(350.dp)
            .shadow(4.dp, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Discount Badge
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFF9800))
                .padding(vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = discount,
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        // Food/Drink Image
        Image(
            painter = imagePainter,
            contentDescription = name,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Restaurant Name
        Text(
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        // Cuisine Category
        Text(
            text = category,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))

        // Delivery Distance
        Text(
            text = "📍 $deliveryDistance",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        // Delivery Price
        Text(
            text ="\uD83D\uDEF5 $deliveryPrice",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}@Composable
fun TopShopsSection(isDarkMode: Boolean) {
    val topShops = listOf(
        Shop(R.drawable.lucky, "Lucky Supermarket", "15 min"),
        Shop(R.drawable.miniso, "Miniso", "20 min"),
        Shop(R.drawable.aeon, "Aeon (Sen Sok Supermarket)", "10 min"),
        Shop(R.drawable.chipmong, "Chip Mong Supermarket", "25 min"),
        Shop(R.drawable.bigc, "Big C Mini", "15 min"),
        Shop(R.drawable.boncafe, "BONCAFE", "20 min"),
        Shop(R.drawable.guadian, "Guardian", "10 min"),
        Shop(R.drawable.gtv, "Green Town Vegetable", "25 min")
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(topShops.size) { index ->
            TopShopItem(shop = topShops[index], textColor = if (isDarkMode) Color.White else Color.Black)
        }
    }
}

@Composable
fun TopShopItem(shop: Shop, textColor: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(15.dp)
            .width(120.dp)
    ) {
        Image(
            painter = painterResource(id = shop.imageResId),
            contentDescription = shop.name,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = shop.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier.padding(vertical = 5.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = shop.distance,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 5.dp)
        )
    }
}

data class Shop(val imageResId: Int, val name: String, val distance: String)