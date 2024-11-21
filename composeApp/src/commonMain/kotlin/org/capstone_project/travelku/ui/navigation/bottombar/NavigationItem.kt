package org.capstone_project.travelku.ui.navigation.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import org.capstone_project.travelku.ui.icon.ExploreSelected
import org.capstone_project.travelku.ui.icon.OutlineExplore
import org.capstone_project.travelku.ui.navigation.Screen

sealed class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val iconSelected: ImageVector,
    val screen: Screen,
) {
    data object Home : NavigationItem(
        title = "Home",
        icon = Icons.Outlined.Home,
        iconSelected = Icons.Filled.Home,
        screen = Screen.Home
    )

    data object Explore : NavigationItem(
        title = "Explore",
        icon = OutlineExplore,
        iconSelected = ExploreSelected,
        screen = Screen.Explore
    )

    data object Profile : NavigationItem(
        title = "Profile",
        icon = Icons.Outlined.Person,
        iconSelected = Icons.Filled.Person,
        screen = Screen.Profile
    )
}