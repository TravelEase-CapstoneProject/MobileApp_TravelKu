package org.capstone_project.travelku.ui.presentation.screen.main.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil3.compose.SubcomposeAsyncImage
import org.capstone_project.travelku.ui.presentation.components.TravelCard
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {
    val scrollState = rememberLazyListState()
    val isScrolled = remember {
        derivedStateOf {
            scrollState.firstVisibleItemScrollOffset > 0 || scrollState.firstVisibleItemIndex > 0
        }
    }

    val username = viewModel.username.collectAsState()

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Halo ${username.value}!",
                subtitle = "Selamat datang di Travelku",
                isScrolled = isScrolled.value,
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            state = scrollState
        ) {

            item {
                Text(
                    "Travel Terpopuler",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(10) { index ->
                        TravelCard(
                            imageUrl = "https://sanjayatour.com/wp-content/uploads/2020/06/Agen-Travel-Jakarta.jpg",
                            title = "Travel $index",
                            location = "Location $index",
                            rating = 4.5f,
                            onClick = { },
                            onRetry = { },
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                }
            }
            item {
                Text(
                    "Yang lagi promo nih",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                PromotionBanner()
            }
            items(15) { index ->
                Text(
                    text = "Item $index",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    subtitle: String,
    isScrolled: Boolean,
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    modifier: Modifier = Modifier
) {
    // Animate the padding of the Column
    val verticalPadding by animateDpAsState(
        targetValue = if (isScrolled) 4.dp else 16.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "verticalPadding"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = verticalPadding, horizontal = 16.dp)
            .windowInsetsPadding(windowInsets),
    ) {
        // Animate the title and subtitle visibility
        AnimatedVisibility(
            visible = !isScrolled,
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = 350,
                    easing = FastOutSlowInEasing
                )
            ) + slideInVertically(
                initialOffsetY = { -30 },
                animationSpec = tween(350, easing = FastOutSlowInEasing)
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = 250,
                    easing = FastOutSlowInEasing
                )
            ) + slideOutVertically(
                targetOffsetY = { -30 },
                animationSpec = tween(250, easing = FastOutSlowInEasing)
            )
        ) {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Animate the search bar position and alpha
        val searchBarPadding by animateDpAsState(
            targetValue = if (isScrolled) 0.dp else 8.dp,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessLow
            ),
            label = "searchBarPadding"
        )

        val searchBarAlpha by animateFloatAsState(
            targetValue = if (isScrolled) 1f else 0.9f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessLow
            ),
            label = "searchBarAlpha"
        )

        SearchComponent(
            navigateToSearch = {
                // Navigate to search screen
            },
            modifier = Modifier
                .padding(top = searchBarPadding)
                .alpha(searchBarAlpha),
        )
    }
}

@Composable
fun SearchComponent(
    modifier: Modifier = Modifier,
    navigateToSearch: () -> Unit,
) {
    var searchQuery by remember { mutableStateOf("") }
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
            placeholder = { Text("Temukan travel") },
            trailingIcon = { Icon(Icons.Default.Search, contentDescription = "Cari") },
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navigateToSearch()
                },
        )
    }
}

@Composable
fun PromotionBanner(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    HorizontalPager(
        state = pagerState,
        beyondViewportPageCount = 2,
        contentPadding = PaddingValues(16.dp),
    ) { page ->
        BannerItem(
            imageUrl = "https://sanjayatour.com/wp-content/uploads/2020/06/Agen-Travel-Jakarta.jpg",
            title = "Promo Travel $page",
            onRetry = { },
            onClick = { },
            modifier = Modifier
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (iteration == pagerState.currentPage) {
                MaterialTheme.colorScheme.primary
            } else {
                Color.Gray
            }
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(8.dp)

            )
        }
    }
}

@Composable
fun BannerItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    onRetry: () -> Unit,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.padding(end = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
        ) {
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = title,
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center)
                    )
                },
                error = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Error",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp)
                        )
                        Button(
                            onClick = { onRetry() },
                            modifier = Modifier.align(Alignment.BottomCenter)
                        ) {
                            Text(text = "Retry")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomStart),
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Button(
                        onClick = onClick,
                        modifier = Modifier
                            .padding(top = 8.dp)
                    ) {
                        Text(text = "Lihat Promo")
                    }
                }
            }
        }
    }
}

@Composable
fun SettingList(modifier: Modifier = Modifier) {


}