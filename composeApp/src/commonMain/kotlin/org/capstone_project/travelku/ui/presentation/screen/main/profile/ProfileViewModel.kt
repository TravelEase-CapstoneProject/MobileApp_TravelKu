package org.capstone_project.travelku.ui.presentation.screen.main.profile

import kotlinx.coroutines.flow.MutableStateFlow
import org.capstone_project.travelku.core.domain.usecase.AuthUseCase

class ProfileViewModel(private val useCase: AuthUseCase) {

    private val _uiState = MutableStateFlow(ProfileState())

    private fun getProfile() {}

    private fun logout() {}
}