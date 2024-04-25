package com.example.allcollections.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allcollections.collection.UserCollection
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class CollectionViewModel : ViewModel() {

    fun saveCollection(name: String, category: String, description: String, iduser: String?, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        viewModelScope.launch {
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
                        onSuccess()
                    }
                    .addOnFailureListener { e ->
                        onFailure("Errore durante il salvataggio della collezione: s${e.message}")
                    }
            } else {
                onFailure("Utente non autenticato")
            }
        }
    }

    fun getCollections(iduser: String?, onSuccess: (List<UserCollection>) -> Unit, onFailure: (String) -> Unit) {
        viewModelScope.launch {
            if (iduser != null) {
                val db = Firebase.firestore

                db.collection("collections")
                    .whereEqualTo("iduser", iduser)
                    .get()
                    .addOnSuccessListener { documents ->
                        val collections = documents.map { document ->
                            document.toObject(UserCollection::class.java)
                        }
                        onSuccess(collections)
                    }
                    .addOnFailureListener { e ->
                        onFailure("Errore durante il recupero delle collezioni: ${e.message}")
                    }
            } else {
                onFailure("Utente non autenticato")
            }
        }
    }

}
