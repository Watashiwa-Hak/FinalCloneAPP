package com.hakudesu.finalcloneapp.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.hakudesu.finalcloneapp.R
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.google.accompanist.systemuicontroller.rememberSystemUiController

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
    val bgColor = if (isDarkMode) Color.Black else Color.White
    val systemUiController = rememberSystemUiController()
    var searchText by remember { mutableStateOf("") }


    val translations = mapOf(
        "en" to mapOf(
            "deliveringTo" to "Delivering To",
            "orderNow" to "Order Now",
            "searchBox" to "Search restaurants and groceries...",
            "categoriesHeader" to "Categories",
            "nearbyHeader" to "Nearby Restaurants",
            "popRes" to "Popular Restaurants",
            "newArrivals" to "New arrivals up to 50% off",
            "topShops" to " Top Shops",
            "topBrands" to " Top Brands",
            "foodMenu" to "Food Menu",
            "price" to "Price",
        ),
        "kh" to mapOf(
            "deliveringTo" to "កំពុងដឹកជញ្ជូនទៅ",
            "orderNow" to "កម៉្មង់ឥឡូវនេះ",
            "searchBox" to "ស្វែងរកអ្វីៗ...",
            "categoriesHeader" to "ប្រភេទម្ហូប",
            "nearbyHeader" to "ភោជនីយដ្ឋានដែលនៅជិត",
            "popRes" to "ភោជនីយដ្ឋានពេញនិយម",
            "newArrivals" to "មកដល់ថ្មី បញ្ចុះតម្លៃរហូតដល់ 50%",
            "topShops" to "Top ហាង ",
            "topBrands" to "Top ប្រេន ",
            "foodMenu" to "មីនុយម្ហូប",
            "price" to "តម្លៃ",
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
        bottomBar = { Footer(navController) }
    ) { paddingValues ->
        MaterialTheme(
            colors = if (isDarkMode) darkColors() else lightColors()
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Delivering To and Hotel Name
                    item {
                        Text(
                            text = currentTranslation["deliveringTo"]!!,
                            color = Color(0xFF_FF5722),
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
                                color = textColor,
                                modifier = Modifier.padding(8.dp)
                            )
                            IconButton(
                                onClick = { /* Edit action */ },
                                modifier = Modifier
                                    .background(Color.LightGray, CircleShape)
                                    .padding(5.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = android.R.drawable.ic_menu_edit),
                                    contentDescription = "Edit"
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    item { // Search Box
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 9.dp)
                                .clip(RoundedCornerShape(50.dp))
                                .background(Color.White)
                                .border(1.dp, Color.LightGray, RoundedCornerShape(50.dp)) // Fake text box border
                                .clickable { navController.navigate("SearchScreen") } // Click anywhere inside
                                .padding(vertical = 12.dp, horizontal = 16.dp) // Padding for text inside
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search, // Search icon
                                    contentDescription = "Search",
                                    tint = Color.Gray,
                                    modifier = Modifier.size(22.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text
                                Text(
                                    text = currentTranslation["searchBox"] ?: "",
                                    color = Color.Gray,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                        }
                    }


                    // Carousel
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Carousel()
                    }

                    // Order Now Button
                    item {
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
                            Text(
                                text = currentTranslation["orderNow"]!!,
                                color = Color.White,
                                fontSize = 17.sp
                            )
                        }
                    }
                    item{Spacer(modifier = Modifier.height(20.dp))}
                    // Categories Section
                    item {
                        Text(
                            text = currentTranslation["categoriesHeader"]!!,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        CategoriesSection(isDarkMode = isDarkMode)
                    }

                    // Top Shops Section
                    item {
                        Text(
                            text = currentTranslation["topShops"]!!,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        TopShopsSection(isDarkMode = isDarkMode)
                    }

                    // Top Brands Section
                    item {
                        Text(
                            text = currentTranslation["topBrands"]!!,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        TopBrandsSection(isDarkMode = isDarkMode)
                    }

                    // Nearby Restaurants Section
                    item {
                        Text(
                            text = currentTranslation["nearbyHeader"]!!,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                                .background(Color.LightGray)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.map),
                                contentDescription = "Background Image",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .alpha(0.5f),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                text = "Google Map will be here",
                                modifier = Modifier.align(Alignment.Center),
                                color = textColor,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    item{Spacer(modifier = Modifier.height(20.dp))}
                    // Popular Restaurants Section
                    item {
                        Text(
                            text = currentTranslation["popRes"]!!,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        RestaurantScroll()
                    }

                    // New Arrivals Section
                    item {
                        Text(
                            text = currentTranslation["newArrivals"]!!,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        NewArrivalScroll()
                    }

                    // Food List Section
                    item {
                        Text(
                            text = currentTranslation["foodMenu"] ?: "Food Menu",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                    itemsIndexed(foodList) { index, food ->
                        FoodCard(navController = navController, food = food, isDarkMode = isDarkMode, textColor = textColor)
                    }
                }
            }
        }
    }
}

@Composable
fun Carousel() {
    val images = listOf(
        R.drawable.banner,
        R.drawable.banner1,
        R.drawable.banner3,
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

        Spacer(modifier = Modifier.height(18.dp))

        // Custom Pagination Indicators
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(images.size) { index ->
                val isActive = index == pagerState.currentPage
                val color = if (isActive) Color(0xFF, 0x57, 0x22, 0xFF) // RGB for active (purple)
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
            imagePainter = painterResource(id = R.drawable.burgerfood),
            name = "Burger King",
            category = "American, Fast Food",
            discount = "Up to 40% OFF",
            deliveryDistance = "3.0 km",
            deliveryPrice = "$2.99"
        )
        NewArrivalCard(
            imagePainter = painterResource(id = R.drawable.sushifood),
            name = "Sushi Spot",
            category = "Japanese, Seafood",
            discount = "Up to 30% OFF",
            deliveryDistance = "1.8 km",
            deliveryPrice = "$6.99"
        )
        NewArrivalCard(
            imagePainter = painterResource(id = R.drawable.pastacornerfood),
            name = "Pasta corner",
            category = "Italian, Vegetarian",
            discount = "Up to 35% OFF",
            deliveryDistance = "2.2 km",
            deliveryPrice = "$1.99"
        )
        NewArrivalCard(
            imagePainter = painterResource(id = R.drawable.steakhousefood),
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

@Composable
fun TopBrandsSection(isDarkMode: Boolean) {
    val topBrands = listOf(
        Brand(R.drawable.dairyqueenlogo, "Diary Queen", "15-20 min"),
        Brand(R.drawable.starbucklogo, "Starbucks", "20-30 min"),
        Brand(R.drawable.texaslogo, "Texas Chicken", "10-20 min"),
        Brand(R.drawable.pizzalogo, "The Pizza Company", "25-35 min"),
        Brand(R.drawable.chatimelogo, "Chatime", "15-20 min"),
        Brand(R.drawable.dominologo, "Domino's Pizza", "20-30 min"),
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(topBrands.size) { index ->
            TopBrandsItem(brand = topBrands[index], textColor = if (isDarkMode) Color.White else Color.Black)
        }
    }
}

@Composable
fun TopBrandsItem(brand: Brand, textColor: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(15.dp)
            .width(120.dp)
    ) {
        Image(
            painter = painterResource(id = brand.imageResId),
            contentDescription = brand.name,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = brand.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier.padding(vertical = 5.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = brand.distance,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 5.dp)
        )
    }
}

data class Brand(val imageResId: Int, val name: String, val distance: String)





@Composable
fun Footer(navController: NavController) {
    // Footer content
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        backgroundColor = Color(0xFFF6F6F6),
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Food Icon and Label
            FooterItem(
                icon = "🍔",
                label = "Food",
                onClick = { navController.navigate("HomeScreen") }
            )

            // Restaurant Icon and Label
            FooterItem(
                icon = "🍽️",
                label = "Restaurant",
                onClick = {navController.navigate("RestaurantScreen") }
            )

            // Search Icon and Label
            FooterItem(
                icon = Icons.Default.ShoppingCart,
                label = "My Cart",
                onClick = {navController.navigate("CartScreen")}
            )

            // Account Icon and Label
            FooterItem(
                icon = Icons.Default.Person,
                label = "Account",
                onClick = {navController.navigate("ProfileScreen")}
            )
        }
    }
}

@Composable
fun FooterItem(icon: Any, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        when (icon) {
            is ImageVector -> Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.size(24.dp),
                tint = Color(0xFFFF5722)
            )
            is String -> Text(
                text = icon, // Use Text for emojis
                fontSize = 24.sp
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Black // Customize text color
        )
    }
}


data class Food(
    val imageRes: Int, // Image resource ID
    val name: String, // Name of the food
    val price: String, // Price of the food
    val description: String // Optional description
)

val foodList: List<Food> = listOf(
    Food(R.drawable.pizzafood, "Hawaiian Pizza", "$12.75", "Pizza with ham and pineapple"),
    Food(R.drawable.burgerfood, "Cheeseburger", "$8.50", "Classic beef cheeseburger"),
    Food(R.drawable.sushifood, "Sushi Platter", "$15.00", "Assorted sushi rolls"),
    Food(R.drawable.steakhousefood, "Tomahawk Steak", "$10.25", "Juicy grilled steak"),
    Food(R.drawable.pizzafood, "Hawaiian Pizza", "$12.75", "Pizza with ham and pineapple"),
    Food(R.drawable.burgerfood, "Cheeseburger", "$8.50", "Classic beef cheeseburger"),
    Food(R.drawable.sushifood, "Sushi Platter", "$15.00", "Assorted sushi rolls"),
    Food(R.drawable.steakhousefood, "Tomahawk Steak", "$10.25", "Juicy grilled steak"),
    Food(R.drawable.pizzafood, "Hawaiian Pizza", "$12.75", "Pizza with ham and pineapple"),
    Food(R.drawable.burgerfood, "Cheeseburger", "$8.50", "Classic beef cheeseburger"),
    Food(R.drawable.sushifood, "Sushi Platter", "$15.00", "Assorted sushi rolls"),
    Food(R.drawable.steakhousefood, "Tomahawk Steak", "$10.25", "Juicy grilled steak")
)

@Composable
fun FoodList(
    navController: NavController,
    isDarkMode: Boolean,
    textColor: Color,
    translation: Map<String, String>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = translation["foodMenu"] ?: "Food Menu",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier.padding(bottom = 16.dp),

            )
        }
        itemsIndexed(foodList) { index, food ->
            FoodCard(navController = navController, food = food, isDarkMode = isDarkMode, textColor = textColor)
        }
    }
}

@Composable
fun FoodCard(navController: NavController, food: Food, isDarkMode: Boolean, textColor: Color) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate("FoodDetailsScreen") }, // Navigate to the correct route
        shape = RoundedCornerShape(10.dp),
        color = if (isDarkMode) Color(0xFF1E1E1E) else Color.White
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = food.imageRes),
                contentDescription = food.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = food.name,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = food.price,
                    fontSize = 16.sp,
                    color = Color(0xFF4CAF50)
                )
                Text(
                    text = food.description,
                    fontSize = 14.sp,
                    color = if (isDarkMode) Color.LightGray else Color.Gray
                )
            }
        }
    }
}
