package com.example.allcollections

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.allcollections.navigation.AppNavigation
import com.example.allcollections.navigation.Screens
import com.example.allcollections.viewModel.ProfileViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.initialize

@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Firebase.initialize(this)
        setContent {
            val navController = rememberNavController()
            val viewModel: ProfileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
            val currentUser = FirebaseAuth.getInstance().currentUser

            val startDestination = if (currentUser != null) {
                Screens.HomeScreen.name
            } else {
                Screens.LoginScreen.name
            }

            AppNavigation(navController, viewModel, startDestination)
        }
    }
}



