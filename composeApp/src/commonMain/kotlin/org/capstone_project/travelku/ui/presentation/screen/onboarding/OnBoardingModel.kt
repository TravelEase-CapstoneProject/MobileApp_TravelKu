package org.capstone_project.travelku.ui.presentation.screen.onboarding

import org.jetbrains.compose.resources.DrawableResource
import travelku.composeapp.generated.resources.Res
import travelku.composeapp.generated.resources.onboard1
import travelku.composeapp.generated.resources.onboard2
import travelku.composeapp.generated.resources.onboard3

sealed class OnBoardingModel(
    val image: DrawableResource,
    val title: String,
    val description: String,
) {

    data object OnBoarding1 : OnBoardingModel(
        image = Res.drawable.onboard1,
        title = "Memesan dimana saja",
        description = "Dengan aplikasi TravelKu anda bisa membooking tiket dimana dan kapan saja"
    )
    data object OnBoarding2 : OnBoardingModel(
        image = Res.drawable.onboard2,
        title = "Rute yang lebih jelas",
        description = "Dengan TravelKu anda lebih mudah untuk menentukan lokasi berangkat dan tujuan"
    )
    data object OnBoarding3 : OnBoardingModel(
        image = Res.drawable.onboard3,
        title = "Transaksi lebih mudah",
        description = "Travelku menawarkan fitur transaksi dengan cash, transfer dan DP"
    )
}