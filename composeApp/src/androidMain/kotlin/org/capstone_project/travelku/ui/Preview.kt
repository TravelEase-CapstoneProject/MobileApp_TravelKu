package org.capstone_project.travelku.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.capstone_project.travelku.R
import org.capstone_project.travelku.ui.presentation.components.TravelCard

@Preview
@Composable
private fun TravelCardPrev() {
    TravelCard(
        imageUrl = R.drawable.jpcompose.toString(),
        title = "Travel Agen 1",
        location = "PKU - Inhil",
        rating = 4.5f,
        onClick = {},
        onRetry = {}
    )
}