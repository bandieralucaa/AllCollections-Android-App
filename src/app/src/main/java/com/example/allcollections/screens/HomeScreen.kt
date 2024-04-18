package com.example.allcollections.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Due card per riga
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp) // Spazio tra le card
    ) {
        items(50) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .aspectRatio(1f) // Stessa dimensione delle card
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = "Face Icon",
                        modifier = Modifier.size(48.dp) // Dimensione dell'icona
                    )
                    Text(text = "Item $index")
                }
            }
        }
    }
}
