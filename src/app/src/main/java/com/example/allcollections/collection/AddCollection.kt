package com.example.allcollections.collection

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.allcollections.navigation.Screens
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun AddCollection(navController: NavController) {

    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val iduser = Firebase.auth.currentUser?.uid

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
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

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Nome") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text(text = "Categoria") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text(text = "Descrzione") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            if (iduser != null) {
                val db = Firebase.firestore

                val collectionData = hashMapOf(
                    "name" to name,
                    "category" to category,
                    "description" to description,
                    "iduser" to iduser
                )

                db.collection("collections")
                    .add(collectionData)
                    .addOnSuccessListener { documentReference ->
                        // Il salvataggio è avvenuto con successo
                        Log.d("Firestore", "Collezione salvata con successo: ${documentReference.id}")
                        navController.navigate(Screens.ProfileScreen.name)
                    }
                    .addOnFailureListener { e ->
                        // Si è verificato un errore durante il salvataggio
                        Log.e("Firestore", "Errore durante il salvataggio della collezione", e)
                        errorMessage = "Errore durante il salvataggio della collezione"
                    }
            } else {
                errorMessage = "Utente non autenticato"
            }
        }) {
            Text(text = "Salva collezione")
        }


        errorMessage?.let { message ->
            Text(
                text = message,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}
