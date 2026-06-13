package com.example.community.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.compositionLocalOf

val LocalDrawerState = compositionLocalOf<DrawerState> {
    error("No DrawerState provided — wrap your composable tree with CompositionLocalProvider(LocalDrawerState provides drawerState)")
}
