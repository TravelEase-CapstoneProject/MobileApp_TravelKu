package org.capstone_project.travelku.ui.presentation.screen.auth.register

sealed class RegisterEvent {
    data class OnEmailChanged(val email: String) : RegisterEvent()
    data class OnPasswordChanged(val password: String) : RegisterEvent()
    data class OnLoginClicked(val email: String, val password: String) : RegisterEvent()
    data class SetLoadingState(val isLoading: Boolean) : RegisterEvent()
    data object ResetState : RegisterEvent()
}