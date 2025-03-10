package com.hakudesu.finalcloneapp

import com.hakudesu.finalcloneapp.screen.CartScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hakudesu.finalcloneapp.screen.LoginScreen
import com.hakudesu.finalcloneapp.screen.RegisterScreen
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hakudesu.finalcloneapp.screen.AboutScreen
import com.hakudesu.finalcloneapp.screen.FoodDetailsScreen
import com.hakudesu.finalcloneapp.screen.Footer
import com.hakudesu.finalcloneapp.screen.GooglePasswordScreen
import com.hakudesu.finalcloneapp.screen.GoogleScreen
import com.hakudesu.finalcloneapp.screen.HomeScreen
import com.hakudesu.finalcloneapp.screen.InfoScreen
import com.hakudesu.finalcloneapp.screen.OTPScreen
import com.hakudesu.finalcloneapp.screen.PasswordScreen
import com.hakudesu.finalcloneapp.screen.PaymentMethodScreen
import com.hakudesu.finalcloneapp.screen.PaymentScreen
import com.hakudesu.finalcloneapp.screen.ProfileScreen
import com.hakudesu.finalcloneapp.screen.RestaurantProfileScreen
import com.hakudesu.finalcloneapp.screen.RestaurantScreen
import com.hakudesu.finalcloneapp.screen.SearchScreen
import com.hakudesu.finalcloneapp.screen.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Screen()
            }
        }
    }

@Composable
fun Screen(){
    val navController = rememberNavController() // Create NavController
    AppNavigation(navController)
}

@Composable
fun AppNavigation(navController: NavController) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("LoginScreen") { LoginScreen(navController) }
        composable("RegisterScreen") { RegisterScreen(navController) }
        composable("PasswordScreen") { PasswordScreen(navController) }
        composable("OTPScreen") { OTPScreen(navController) }
        composable("HomeScreen") { HomeScreen(navController) }
        composable("cartScreen") { CartScreen(navController) }
        composable("restaurantScreen") { RestaurantScreen(navController) }
        composable("FoodDetailsScreen") { FoodDetailsScreen(navController) }
        composable("SearchScreen") { SearchScreen(navController) }
        composable("AboutScreen") { AboutScreen(navController) }


        composable(
            "restaurantProfile/{name}/{rating}/{deliveryTime}/{cuisine}/{imageRes}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("rating") { type = NavType.StringType },
                navArgument("deliveryTime") { type = NavType.StringType },
                navArgument("cuisine") { type = NavType.StringType },
                navArgument("imageRes") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val rating = backStackEntry.arguments?.getString("rating") ?: ""
            val deliveryTime = backStackEntry.arguments?.getString("deliveryTime") ?: ""
            val cuisine = backStackEntry.arguments?.getString("cuisine") ?: ""
            val imageRes = backStackEntry.arguments?.getInt("imageRes") ?: R.drawable.logo

            RestaurantProfileScreen(navController, name, rating, deliveryTime, cuisine, imageRes)
        }


        composable("ProfileScreen") { ProfileScreen(navController) }


        composable("PaymentMethodScreen") { PaymentMethodScreen(navController) }
        composable(
            route = "infoScreen/{totalPrice}",
            arguments = listOf(
                navArgument("totalPrice") { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            // Retrieve totalPrice from arguments
            val totalPrice = backStackEntry.arguments?.getFloat("totalPrice") ?: 0f
            InfoScreen(navController, totalPrice)
        }
        composable("GoogleScreen") { GoogleScreen(navController) }
        composable("Footer") { Footer(navController) }
        composable("GooglePasswordScreen/{email}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            GooglePasswordScreen(navController, email)
        }
        composable(
            route = "paymentScreen/{fullName}/{tel}/{address}/{totalPrice}",
            arguments = listOf(
                navArgument("fullName") { type = NavType.StringType },
                navArgument("tel") { type = NavType.StringType },
                navArgument("address") { type = NavType.StringType },
                navArgument("totalPrice") { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val fullName = backStackEntry.arguments?.getString("fullName") ?: ""
            val tel = backStackEntry.arguments?.getString("tel") ?: ""
            val address = backStackEntry.arguments?.getString("address") ?: ""
            val totalPrice = backStackEntry.arguments?.getFloat("totalPrice") ?: 0f
            PaymentScreen(
                navController = navController,
                fullName = fullName,
                tel = tel,
                address = address,
                totalPrice = totalPrice
            )
        }
    }
}

