package com.example.allcollections.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.time.LocalDate

@Composable
fun RegisterScreen(navController: NavController) {

    var name by remember {
        mutableStateOf("")
    }

    var surname by remember {
        mutableStateOf("")
    }

    var dateOfBirth by remember {
        mutableStateOf(LocalDate.now())
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = name, onValueChange = {
            name = it
        }, label = {
            Text(text = "Nome")
        })

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(value = surname, onValueChange = {
            surname = it
        }, label = {
            Text(text = "Cognome")
        })

        Spacer(modifier = Modifier.height(10.dp))

        /*datapicker
        *
        * DatePicker(
            selectedDate = dateOfBirthState.value,
            onDateChange = { dateOfBirthState.value = it },
            modifier = Modifier.padding(16.dp),
            label = { Text("Data di nascita") }
        )
        *
        *
        * */

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(value = email, onValueChange = {
            email = it
        }, label = {
            Text(text = "Indirizzo email")
        })

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = password, onValueChange = {
            password = it
        }, label = {
            Text(text = "Password")
        }, visualTransformation = PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { }) {
            Text(text = "Registrati")
        }
    }
}