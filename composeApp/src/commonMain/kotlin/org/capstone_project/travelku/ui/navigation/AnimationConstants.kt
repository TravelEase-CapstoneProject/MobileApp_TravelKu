package org.capstone_project.travelku.ui.navigation

import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally

object AnimationConstants {
    private const val ENTER_MILLIS = 250
    private const val EXIT_MILLIS = 250

    val enterTransition = fadeIn(
        animationSpec = tween(
            durationMillis = ENTER_MILLIS,
            easing = FastOutLinearInEasing,
        ),
    )

    val exitTransition = fadeOut(
        animationSpec = tween(
            300, easing = LinearEasing
        )
    ) + slideOutHorizontally(
        animationSpec = tween(300, easing = EaseOut),
    )
}