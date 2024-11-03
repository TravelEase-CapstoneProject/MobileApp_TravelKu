package org.capstone_project.travelku.ui.presentation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import org.capstone_project.travelku.core.di.koinModules
import org.capstone_project.travelku.ui.navigation.RootNavHost
import org.capstone_project.travelku.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

@Composable
@Preview
fun App() {
    KoinContext {
        AppTheme {
            Surface {
                RootNavHost()
            }
        }
    }
}

fun initializeKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(koinModules)
    }
}