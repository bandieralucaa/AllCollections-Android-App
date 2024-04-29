package com.example.allcollections.collection

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.allcollections.navigation.Screens
import com.example.allcollections.viewModel.CollectionViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@Composable
fun MyCollections(navController: NavController, viewModel: CollectionViewModel) {
    val collections = remember { mutableStateOf(emptyList<UserCollection>()) }
    val iduser = Firebase.auth.currentUser?.uid

    val snackbarHostState = remember { SnackbarHostState()   }

    LaunchedEffect(key1 = Unit) {
        viewModel.getCollections(iduser,
            onSuccess = { collections.value = it },
            onFailure = { error ->
                launch {
                    snackbarHostState.showSnackbar("Errore: $error")
                }
            }
        )
    }



    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Due card per riga
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp) // Spazio tra le card
        ) {
            items(collections.value.size) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .aspectRatio(1f) // Stessa dimensione delle card
                        .clickable {
                            val selectedCollection = collections.value[index]
                            navController.navigate("${Screens.CollectionDetailScreen.name}/$iduser/${selectedCollection.name}/${selectedCollection.category}/${selectedCollection.description}")
                        }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Face Icon",
                            modifier = Modifier.size(48.dp) // Dimensione dell'icona
                        )
                        Text(text = collections.value[index].name)
                    }
                }
            }
        }
    }
    SnackbarHost(hostState = snackbarHostState)

}
