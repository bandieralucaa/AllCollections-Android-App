package com.example.allcollections.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
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
        icon = Icons.Default.Search,
        route = Screens.SearchPage.name
    ),
    NavItem(
        icon = Icons.Default.Send,
        route = Screens.ChatScreen.name
    ),
    NavItem(
        icon = Icons.Default.Person,
        route = Screens.ProfileScreen.name
    )
)