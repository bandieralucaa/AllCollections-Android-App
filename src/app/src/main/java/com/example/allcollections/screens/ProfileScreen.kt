package com.example.allcollections.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.allcollections.R
import com.example.allcollections.navigation.Screens

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = {
                    navController.navigate(Screens.SettingsScreen.name)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Icona Impostazioni"
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.profile),
            contentDescription = "Icona Person",
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Il tuo nome utente",
            fontFamily = FontFamily.Serif,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate(Screens.MyCollections.name)
        }) {
            Text(text = "Vedi le tue collezioni")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate(Screens.AddCollection.name)
        }) {
            Text(text = "Crea una nuova collezione")
        }
    }
}
