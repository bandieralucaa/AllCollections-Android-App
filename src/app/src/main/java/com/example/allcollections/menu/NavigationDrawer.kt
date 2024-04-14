package com.example.allcollections.menu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(navController: NavController){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val scope = rememberCoroutineScope()

    val items = listOf(
        DrawerItem(icon = Icons.Default.Home, label = "Home", route = Routes.HOME_PAGE),
        DrawerItem(icon = Icons.Default.Person, label = "Profilo", route = Routes.PROFILE_PAGE),
        DrawerItem(icon = Icons.Default.Settings, label = "Impostazioni", route = Routes.SETTINGS_PAGE),
    )

    var selectedItem by remember {
        mutableStateOf(items[0])
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            ModalDrawerSheet {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 64.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Header", style = MaterialTheme.typography.headlineLarge)
                }
                items.forEach {item->
                    NavigationDrawerItem(
                        label = { Text(text = item.label) },
                        selected = item == selectedItem,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                                navController.navigate(item.route)
                            }
                            selectedItem = item
                        },
                        icon = {
                            Icon(imageVector = item.icon, contentDescription = item.label)
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        content = {

        }
    )
}

data class DrawerItem(
    val icon: ImageVector,
    val label: String,
    val route: String
)

