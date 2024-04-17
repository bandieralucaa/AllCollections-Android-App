package com.example.allcollections.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.allcollections.screens.HomeScreen
import com.example.allcollections.screens.LoginScreen
import com.example.allcollections.screens.ProfileScreen
import com.example.allcollections.screens.RegisterScreen
import com.example.allcollections.screens.SettingsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            if (currentDestination?.route !in listOf(Screens.LoginScreen.name, Screens.RegisterScreen.name)) {
                NavigationBar {
                    listOfNavItems.forEach { navItem ->
                        NavigationBarItem(
                            selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                            onClick = {
                                navController.navigate(navItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = navItem.icon,
                                    contentDescription = null
                                )
                            },
                            label = {
                                Text(text = navItem.label)
                            }
                        )
                    }
                }
            }
        }
    ) {paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.LoginScreen.name,
            modifier = Modifier
                .padding(paddingValues)
        ){
            composable(route = Screens.LoginScreen.name) {
                LoginScreen(navController)
            }
            composable(route = Screens.HomeScreen.name) {
                HomeScreen()
            }
            composable(route = Screens.ProfileScreen.name) {
                ProfileScreen()
            }
            composable(route = Screens.SettingsScreen.name) {
                SettingsScreen()
            }
            composable(route = Screens.RegisterScreen.name) {
                RegisterScreen(navController)
            }
        }
    }
}