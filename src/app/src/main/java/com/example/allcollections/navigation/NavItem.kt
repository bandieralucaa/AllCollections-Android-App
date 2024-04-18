package com.example.allcollections.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val icon: ImageVector,
    val route: String
)

val listOfNavItems = listOf(
    NavItem(
        icon = Icons.Default.Home,
        route = Screens.HomeScreen.name
    ),
    NavItem(
        icon = Icons.Default.Person,
        route = Screens.ProfileScreen.name
    ),
    NavItem(
        icon = Icons.Default.Settings,
        route = Screens.SettingsScreen.name
    )
)