package com.example.allcollections.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(50) { index ->
            ListItem(
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = "Face Icon"
                    )
                },
                headlineContent = { Text(text = "Item $index") }
            )
        }
    }
}
