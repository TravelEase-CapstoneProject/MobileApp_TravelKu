package org.capstone_project.travelku.ui.presentation.screen.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.capstone_project.travelku.ui.presentation.screen.common.SharedViewModel
import org.koin.compose.viewmodel.koinViewModel

private const val SplashTimeOut: Long = 3000

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateToOnBoarding: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: SharedViewModel = koinViewModel()
) {

    val onBoardingCompleted by viewModel.onBoardingCompleted.collectAsState()
    val session = viewModel.session.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.checkOnBoardingCompleted()
        viewModel.checkSession()
        delay(SplashTimeOut)
        if (onBoardingCompleted) {
            if (session.value?.token!!.isNotEmpty()) {
                navigateToHome()
            } else {
                navigateToLogin()
            }
        } else {
            navigateToOnBoarding()
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Selamat Datang di TravelKu", fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
}