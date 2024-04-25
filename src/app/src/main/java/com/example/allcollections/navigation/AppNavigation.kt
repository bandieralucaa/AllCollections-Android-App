package com.example.allcollections.navigation

import SearchScreen
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
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.allcollections.collection.AddCollection
import com.example.allcollections.collection.MyCollections
import com.example.allcollections.screens.CameraScreen
import com.example.allcollections.screens.ChatScreen
import com.example.allcollections.screens.HomeScreen
import com.example.allcollections.screens.LoginScreen
import com.example.allcollections.screens.ProfileScreen
import com.example.allcollections.screens.RegisterScreen
import com.example.allcollections.screens.SettingsScreen
import com.example.allcollections.viewModel.CollectionViewModel
import com.example.allcollections.viewModel.ProfileViewModel
import com.example.allcollections.viewModel.ViewModelContainer


@Composable
fun AppNavigation(navController: NavHostController, viewModelContainer: ViewModelContainer, startDestination: String) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            if (currentDestination?.route !in listOf(Screens.LoginScreen.name, Screens.RegisterScreen.name, Screens.CameraScreen.name)) {
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
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = Screens.LoginScreen.name) {
                LoginScreen(navController, viewModelContainer.profileViewModel)
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
                SearchScreen()
            }
            composable(route = Screens.SettingsScreen.name) {
                SettingsScreen(navController, viewModelContainer.profileViewModel)
            }
            composable(route = Screens.RegisterScreen.name) {
                RegisterScreen(navController)
            }
            composable(route = Screens.AddCollection.name) {
                AddCollection(navController)
            }
            composable(route = Screens.MyCollections.name) {
                MyCollections(navController, viewModelContainer.collectionViewModel)
            }
            composable(route = Screens.CameraScreen.name) {
                CameraScreen(navController)
            }
        }
    }

}