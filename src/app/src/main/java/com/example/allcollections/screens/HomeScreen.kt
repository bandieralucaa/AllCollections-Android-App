package com.example.allcollections.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.KeyboardArrowDown
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
    Column {
        // Barra superiore con icona di filtro
        FilterBar()

        // Griglia di card
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(50) { index ->
                // Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .aspectRatio(1f)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Face,
                            contentDescription = "Face Icon",
                            modifier = Modifier.size(48.dp)
                        )
                        Text(text = "Item $index")
                    }
                }
            }
        }
    }
}

@Composable
fun FilterBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.Gray), // Sfondo grigio
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Spazio per spingere l'icona a destra
        Spacer(modifier = Modifier.weight(1f))

        // Icona del filtro
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Filter Icon",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
    }
}
