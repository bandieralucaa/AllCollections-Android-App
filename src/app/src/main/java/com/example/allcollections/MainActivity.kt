package com.example.allcollections

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.allcollections.menu.NavigationDrawer
import com.example.allcollections.menu.Routes
import com.example.allcollections.screens.HomeScreen
import com.example.allcollections.screens.LoginScreen
import com.example.allcollections.screens.RegisterScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = Routes.LOGIN_SCREEN) {
                composable(Routes.LOGIN_SCREEN) { LoginScreen(navController) }
                composable(Routes.REGISTER_SCREEN) { RegisterScreen(navController) }
                composable(Routes.HOME_SCREEN) { NavigationDrawer(navController = navController) }

            }
        }

    }
}