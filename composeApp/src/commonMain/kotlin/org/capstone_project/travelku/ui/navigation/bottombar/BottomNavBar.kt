package org.capstone_project.travelku.ui.navigation.bottombar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier, navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val topLevelRoutes = listOf(
        NavigationItem.Home,
        NavigationItem.Explore,
        NavigationItem.Profile
    )

    NavigationBar(
        modifier = modifier
    ) {
        topLevelRoutes.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.hasRoute(item.screen::class) } == true,
                onClick = {
                    item.screen.let {
                        navController.navigate(it) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (currentDestination?.hierarchy?.any { it.hasRoute(item.screen::class) } == true) {
                            item.iconSelected
                        } else {
                            item.icon
                        },
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        text = item.title
                    )
                }
            )
        }
    }
}