package com.example.community.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

val LocalNavBackStack = compositionLocalOf<NavBackStack<NavKey>> {
    error("No NavBackStack provided — wrap your composable tree with CompositionLocalProvider(LocalNavBackStack provides backStack)")
}
