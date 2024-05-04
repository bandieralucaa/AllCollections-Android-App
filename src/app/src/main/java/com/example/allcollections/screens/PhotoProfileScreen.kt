package com.example.allcollections.screens

import android.Manifest
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.allcollections.utils.rememberCameraLauncher
import com.example.allcollections.utils.rememberPermission

@Composable
fun PhotoProfileScreen() {

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
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Aggiungi la tua foto profilo", fontSize = 28.sp)

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = ::takePicture) {
            Text(text = "Scatta una foto")
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

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = { /*TODO*/ }) {
            Text(text = "Scegli dalla galleria")
        }
    }
}