package org.capstone_project.travelku

import androidx.compose.ui.window.ComposeUIViewController
import org.capstone_project.travelku.ui.presentation.App
import org.capstone_project.travelku.ui.presentation.initializeKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initializeKoin()
    }
) {
    App()
}