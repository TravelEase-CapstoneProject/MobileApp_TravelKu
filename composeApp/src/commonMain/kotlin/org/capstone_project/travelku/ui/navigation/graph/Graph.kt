package org.capstone_project.travelku.ui.navigation

import kotlinx.serialization.Serializable

sealed class Graph {
    @Serializable
    data object AuthGraph : Graph()

    @Serializable
    data object HomeGraph : Graph()

    @Serializable
    data object DetailGraph : Graph()

    @Serializable
    data object SearchGraph : Graph()

    @Serializable
    data object ProfileGraph : Graph()
}