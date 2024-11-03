package org.capstone_project.travelku.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.capstone_project.travelku.ui.navigation.graph.authGraph
import org.capstone_project.travelku.ui.presentation.screen.home.HomeScreen
import org.capstone_project.travelku.ui.presentation.screen.splash.SplashScreen

@Composable
fun RootNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash
    ) {
        composable<Screen.Splash>(
            enterTransition = { AnimationConstants.enterTransition},
            exitTransition = { AnimationConstants.exitTransition }
        ) {
            SplashScreen(
                navigateToOnBoarding = {
                    navController.navigate(Screen.OnBoarding) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                navigateToHome = {
                    navController.navigate(Screen.Home) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                navigateToLogin = {
                    navController.navigate(Screen.Login) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
        authGraph(navController)
        composable<Screen.Home> {
            HomeScreen()
        }
    }
}