package com.example.community

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.example.community.navigation.LocalNavBackStack
import com.example.community.navigation.Screen
import com.example.community.ui.CommunitiesScreen
import com.example.community.ui.MainScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

// Required for non-JVM targets (iOS) — reflection-based serialization is unavailable there.
private val navConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(Screen.Announcements::class, Screen.Announcements.serializer())
            subclass(Screen.Communities::class, Screen.Communities.serializer())
        }
    }
}

@Composable
fun App() {
    MaterialTheme {
        val backStack = rememberNavBackStack(navConfig, Screen.Announcements)
        CompositionLocalProvider(LocalNavBackStack provides backStack) {
            NavDisplay(
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                entryProvider = entryProvider {
                    entry<Screen.Announcements> { MainScreen() }
                    entry<Screen.Communities> { CommunitiesScreen() }
                }
            )
        }
    }
}
