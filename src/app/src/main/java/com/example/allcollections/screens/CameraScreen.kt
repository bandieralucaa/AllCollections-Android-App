package com.example.allcollections.screens

import androidx.compose.runtime.Composable
import com.example.allcollections.utils.rememberCameraLauncher
import com.example.allcollections.utils.rememberPermission
import android.Manifest
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.allcollections.navigation.Screens

@Composable
fun CameraScreen(navController: NavController) {
    val ctx = LocalContext.current

    val cameraLauncher = rememberCameraLauncher()

    val cameraPermission = rememberPermission(Manifest.permission.CAMERA) { status ->
        if (status.isGranted) {
            cameraLauncher.captureImage()
        } else {
            Toast.makeText(ctx, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    fun takePicture() =
        if (cameraPermission.status.isGranted) {
            cameraLauncher.captureImage()
        } else {
            cameraPermission.launchPermissionRequest()
        }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Aggiungi Foto Profilo",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = ::takePicture) {
            Text("Scatta una foto")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
        }) {
            Text("Scegli dalla galleria")
        }


        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            navController.navigate(Screens.HomeScreen.name)
        }) {
            Text("In seguito")
        }
    }

    if (cameraLauncher.capturedImageUri.path?.isNotEmpty() == true) {
        AsyncImage(
            ImageRequest.Builder(ctx)
                .data(cameraLauncher.capturedImageUri)
                .crossfade(true)
                .build(),
            "Captured image"
        )
    }
}
