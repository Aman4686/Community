package com.example.community.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CommunitiesScreen() {
    Scaffold(
        containerColor = TopBarBg,
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = { CommunityTopBar(title = "Communities") }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(TopBarBg)
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text("Communities", color = TopBarTextPrimary)
        }
    }
}
