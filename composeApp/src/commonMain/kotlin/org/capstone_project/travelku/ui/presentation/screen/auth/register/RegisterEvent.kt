package org.capstone_project.travelku.ui.presentation.screen.auth.register

import org.capstone_project.travelku.core.data.remote.response.Role

sealed class RegisterEvent {
    data class OnUsernameChanged(val username: String) : RegisterEvent()
    data class OnEmailChanged(val email: String) : RegisterEvent()
    data class OnPasswordChanged(val password: String) : RegisterEvent()
    data class OnRegisterClicked(val username: String, val email: String, val password: String) : RegisterEvent()
    data class SetLoadingState(val isLoading: Boolean) : RegisterEvent()
    data object ResetState : RegisterEvent()
}