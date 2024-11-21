package org.capstone_project.travelku.ui.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object Splash : Screen()

    @Serializable
    data object OnBoarding : Screen()

    @Serializable
    data object Login : Screen()

    @Serializable
    data object Register : Screen()

    @Serializable
    data object Main : Screen()

    @Serializable
    data object Home : Screen()

    @Serializable
    data object Explore : Screen()

    @Serializable
    data object Profile : Screen()

}