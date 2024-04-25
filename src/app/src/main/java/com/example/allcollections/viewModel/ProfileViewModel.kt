package com.example.allcollections.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.allcollections.navigation.Screens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class ProfileViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val _isLoggedIn = mutableStateOf(false)
    val isLoggedIn: State<Boolean> = _isLoggedIn
    private val _loginErrorMessage = mutableStateOf<String?>(null)
    val loginErrorMessage: State<String?> = _loginErrorMessage

    suspend fun getUsername(): String {
        val userId = auth.currentUser?.uid ?: throw Exception("User not logged in")
        val userDocument = db.collection("users").document(userId).get().await()
        return userDocument.getString("username") ?: throw Exception("Username not found")
    }


    fun login(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        if (email.isBlank() || password.isBlank()) {
            _loginErrorMessage.value = "Inserire email e/o password"
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _isLoggedIn.value = true
                    _loginErrorMessage.value = null
                    callback(true, null)
                } else {
                    val errorMessage = when (task.exception) {
                        is FirebaseAuthInvalidUserException -> "L'utente non esiste."
                        is FirebaseAuthInvalidCredentialsException -> "Credenziali non valide."
                        else -> "Errore di login sconosciuto."
                    }
                    callback(false, errorMessage)
                }
            }
    }

    fun logout(callback: () -> Unit) {
        auth.signOut()
        _isLoggedIn.value = false
        callback()
    }
}
