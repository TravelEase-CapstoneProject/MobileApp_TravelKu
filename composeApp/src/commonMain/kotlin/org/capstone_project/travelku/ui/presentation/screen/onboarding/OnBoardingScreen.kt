package org.capstone_project.travelku.ui.presentation.screen.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.capstone_project.travelku.ui.presentation.components.ButtonUI
import org.capstone_project.travelku.ui.presentation.screen.common.SharedViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    onFinished: () -> Unit,
    viewModel: SharedViewModel = koinViewModel()
) {
    val pages = listOf(
        OnBoardingModel.OnBoarding1,
        OnBoardingModel.OnBoarding2,
        OnBoardingModel.OnBoarding3
    )

    val pagerState = rememberPagerState(initialPage = 0) { pages.size }

    val buttonState = remember {
        derivedStateOf {
            when (pagerState.currentPage) {
                pages.size - 1 -> "Mulai"
                else -> "Lanjut"
            }
        }
    }

    val scope = rememberCoroutineScope()
    Scaffold {
        Box(
            modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Column(
                modifier = modifier.fillMaxWidth().align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalPager(state = pagerState) { page ->
                    OnBoardingGraphUI(onBoardingModel = pages[page])
                }
                Spacer(modifier = Modifier.size(20.dp).fillMaxWidth())
                IndicatorUI(pageSize = pages.size, currentPage = pagerState.currentPage)
                Spacer(modifier = Modifier.size(25.dp).fillMaxWidth())
                ButtonUI(text = buttonState.value, modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp)) {
                    scope.launch {
                        if (pagerState.currentPage == pages.size - 1) {
                            viewModel.saveOnBoardingCompleted()
                            onFinished()
                        } else {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun OnBoardingScreenPrev() {
    OnBoardingScreen(onFinished = {})
}
