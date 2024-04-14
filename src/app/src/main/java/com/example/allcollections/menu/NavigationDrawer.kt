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
            Conten2 (
                onMenuIconClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            )
        }
    )
}

data class DrawerItem(
    val icon: ImageVector,
    val label: String,
    val route: String
)


@Composable
fun Content(
    onClick: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = ">>> Swipe >>>")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onClick) {
            Text(text = "Click to open")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Conten2(
    onMenuIconClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onMenuIconClick) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                title = {
                    Text(text = "Menu")
                }
            )
        }
    ) {padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp)
        ){
            items(50){
                ListItem(
                    headlineContent = { Text(text = "Item $it") },
                    leadingContent = {
                        Icon(
                            Icons.Default.Face,
                            contentDescription = null
                        )
                    }
                )
            }
        }

    }
}
