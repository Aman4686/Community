package com.example.community.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen : NavKey {
    @Serializable
    data object Announcements : Screen()

    @Serializable
    data object Communities : Screen()
}
