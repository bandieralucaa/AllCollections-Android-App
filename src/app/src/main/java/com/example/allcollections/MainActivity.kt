package com.example.allcollections

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.allcollections.menu.NavigationDrawer
import com.example.allcollections.menu.Routes
import com.example.allcollections.screens.LoginScreen
import com.example.allcollections.screens.RegisterScreen
import com.example.allcollections.screens.SettingsScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import com.example.allcollections.screens.ProfileScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val coroutineScope = rememberCoroutineScope()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            MainContent(navController, coroutineScope, drawerState)
        }
    }
}


@Composable
fun MainContent(navController: NavHostController, coroutineScope: CoroutineScope, drawerState: DrawerState) {
    Surface(modifier = Modifier.fillMaxSize()) {
        NavHost(navController, startDestination = Routes.LOGIN_SCREEN) {
            composable(Routes.LOGIN_SCREEN) { LoginScreen(navController) }
            composable(Routes.REGISTER_SCREEN) { RegisterScreen(navController) }
            composable(Routes.HOME_SCREEN) { NavigationDrawer(navController, drawerState) }
            composable(Routes.SETTINGS_SCREEN) {
                SettingsScreen(navController = navController, drawerState = drawerState) {
                    coroutineScope.launch {
                        navController.navigateUp()
                    }
                }
            }
            composable(Routes.PROFILE_SCREEN) {
                ProfileScreen(navController = navController, drawerState = drawerState) {
                    coroutineScope.launch {
                        navController.navigateUp()
                    }
                }
            }
        }
    }
}
