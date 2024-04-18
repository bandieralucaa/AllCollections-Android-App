package com.example.allcollections.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun SettingsScreen(navController: NavController) {
    val settingsItems = listOf("Cambia nome", "Cambia password", "Notifiche", "Privacy", "Aiuto")

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
        }

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(settingsItems.size) { index ->
                val setting = settingsItems[index]
                ClickableSettingItem(setting = setting)
                if (index < settingsItems.size - 1) {
                    Divider(color = Color.Gray, thickness = 0.5.dp)
                }
            }
        }
    }
}

@Composable
fun ClickableSettingItem(setting: String) {
    Text(
        text = setting,
        modifier = Modifier
            .clickable { /* Azione da eseguire quando l'elemento viene cliccato */ }
            .padding(vertical = 16.dp, horizontal = 16.dp)
            .fillMaxWidth()
    )
}
