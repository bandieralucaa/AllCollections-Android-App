package com.example.allcollections.apptheme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.allcollections.ui.theme.AllCollectionsTheme

@Composable
fun ThemeToggleContent(themeViewModel: ThemeViewModel) {
    val themeState by themeViewModel.state.collectAsState()

    AllCollectionsTheme(
        darkTheme = when (themeState.theme) {
            Theme.Light -> false
            Theme.Dark -> true
            Theme.System -> isSystemInDarkTheme()
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ThemeScreen(themeState, themeViewModel::changeTheme)
        }
    }
}
