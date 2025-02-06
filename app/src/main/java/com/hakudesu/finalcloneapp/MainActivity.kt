package com.hakudesu.finalcloneapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hakudesu.finalcloneapp.screen.LoginScreen
import com.hakudesu.finalcloneapp.screen.RegisterScreen
import androidx.navigation.compose.rememberNavController
import com.hakudesu.finalcloneapp.screen.GooglePasswordScreen
import com.hakudesu.finalcloneapp.screen.GoogleScreen
import com.hakudesu.finalcloneapp.screen.HomeScreen
import com.hakudesu.finalcloneapp.screen.OTPScreen
import com.hakudesu.finalcloneapp.screen.PasswordScreen

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
    NavHost(navController = navController, startDestination = "LoginScreen") {
        composable("LoginScreen") { LoginScreen(navController) }
        composable("RegisterScreen") { RegisterScreen(navController) }
        composable("PasswordScreen") { PasswordScreen(navController) }
        composable("OTPScreen") { OTPScreen(navController) }
        composable("HomeScreen") { HomeScreen(navController) }
        composable("GoogleScreen") { GoogleScreen(navController) }
        composable("GooglePasswordScreen/{email}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            GooglePasswordScreen(navController, email)
        }
    }
}

