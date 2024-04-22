package com.example.allcollections.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.allcollections.collection.AddCollection
import com.example.allcollections.collection.MyCollections
import com.example.allcollections.collection.ObjectCollection
import com.example.allcollections.screens.ChatScreen
import com.example.allcollections.screens.HomeScreen
import com.example.allcollections.screens.LoginScreen
import com.example.allcollections.screens.ProfileScreen
import com.example.allcollections.screens.RegisterScreen
import com.example.allcollections.screens.SearchPage
import com.example.allcollections.screens.SettingsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            if (currentDestination?.route !in listOf(Screens.LoginScreen.name, Screens.RegisterScreen.name)) {
                NavigationBar(
                    modifier = Modifier.height(55.dp)
                ) {
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
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.LoginScreen.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = Screens.LoginScreen.name) {
                LoginScreen(navController)
            }
            composable(route = Screens.HomeScreen.name) {
                HomeScreen()
            }
            composable(route = Screens.ProfileScreen.name) {
                ProfileScreen(navController)
            }
            composable(route = Screens.ChatScreen.name) {
                ChatScreen()
            }
            composable(route = Screens.SearchPage.name) {
                SearchPage()
            }
            composable(route = Screens.SettingsScreen.name) {
                SettingsScreen(navController)
            }
            composable(route = Screens.RegisterScreen.name) {
                RegisterScreen(navController)
            }
            composable(route = Screens.AddCollection.name) {
                AddCollection(navController)
            }
            composable(route = Screens.MyCollections.name) {
                MyCollections(navController)
            }
            composable(route = Screens.ObjectCollection.name) {
                ObjectCollection(navController)
            }
        }
    }

}