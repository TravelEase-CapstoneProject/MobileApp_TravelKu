package org.capstone_project.travelku.ui.presentation.screen.auth.login

sealed class LoginEvent {
    data class OnEmailChanged(val email: String) : LoginEvent()
    data class OnPasswordChanged(val password: String) : LoginEvent()
    data class OnLoginClicked(val email: String, val password: String) : LoginEvent()
    data class SetLoadingState(val isLoading: Boolean) : LoginEvent()
    data object ResetState : LoginEvent()
}