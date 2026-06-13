package com.example.community

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.example.community.navigation.LocalDrawerState
import com.example.community.navigation.LocalNavBackStack
import com.example.community.navigation.Screen
import com.example.community.ui.CommunitiesScreen
import com.example.community.ui.CommunityDrawerSheet
import com.example.community.ui.MainScreen
import com.example.community.ui.ProfileScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

// Required for non-JVM targets (iOS) — reflection-based serialization is unavailable there.
private val navConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(Screen.Announcements::class, Screen.Announcements.serializer())
            subclass(Screen.Communities::class, Screen.Communities.serializer())
            subclass(Screen.Profile::class, Screen.Profile.serializer())
        }
    }
}

@Composable
fun App() {
    MaterialTheme {
        val backStack = rememberNavBackStack(navConfig, Screen.Announcements)
        val drawerState = rememberDrawerState(DrawerValue.Closed)

        CompositionLocalProvider(
            LocalNavBackStack provides backStack,
            LocalDrawerState provides drawerState,
        ) {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = { CommunityDrawerSheet() },
                scrimColor = Color(0x55000510),
            ) {
                NavDisplay(
                    backStack = backStack,
                    onBack = { backStack.removeLastOrNull() },
                    entryProvider = entryProvider {
                        entry<Screen.Announcements> { MainScreen() }
                        entry<Screen.Communities> { CommunitiesScreen() }
                        entry<Screen.Profile> { ProfileScreen() }
                    }
                )
            }
        }
    }
}
