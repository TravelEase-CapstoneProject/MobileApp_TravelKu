package org.capstone_project.travelku.ui.navigation.graph

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import org.capstone_project.travelku.ui.navigation.Graph
import org.capstone_project.travelku.ui.navigation.Screen
import org.capstone_project.travelku.ui.presentation.screen.auth.login.LoginScreen
import org.capstone_project.travelku.ui.presentation.screen.auth.login.LoginViewModel
import org.capstone_project.travelku.ui.presentation.screen.auth.register.RegisterScreen
import org.capstone_project.travelku.ui.presentation.screen.auth.register.RegisterViewModel
import org.capstone_project.travelku.ui.presentation.screen.onboarding.OnBoardingScreen
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation<Graph.AuthGraph>(startDestination = Screen.OnBoarding) {
        composable<Screen.OnBoarding> {
            OnBoardingScreen(
                onFinished = {
                    navController.navigate(Screen.Login)
                }
            )
        }
        composable<Screen.Login> {
            val viewModel = koinViewModel<LoginViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            LoginScreen(
                onRegisterClick = {
                    navController.navigate(Screen.Register)
                },
                navigateToHome = {
                    navController.navigate(Screen.Home) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                navigateToForgetPassword = {},
                state = state,
                onEvent = viewModel::onEvent
            )
        }
        composable<Screen.Register> {
            val viewModel = koinViewModel<RegisterViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            RegisterScreen(
                onRegisterClick = {},
                onLoginClick = {
                    navController.navigate(Screen.Login) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    }
}
