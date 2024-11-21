package org.capstone_project.travelku.ui.navigation.bottombar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.capstone_project.travelku.ui.navigation.Screen
import org.capstone_project.travelku.ui.presentation.screen.main.explore.ExploreScreen
import org.capstone_project.travelku.ui.presentation.screen.main.home.HomeScreen
import org.capstone_project.travelku.ui.presentation.screen.main.profile.ProfileScreen

@Composable
fun BottomNavHost(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Home,
    ) {
        composable<Screen.Home> {
            HomeScreen()
        }
        composable<Screen.Explore> {
            ExploreScreen()
        }
        composable<Screen.Profile> {
            ProfileScreen()
        }
    }

}