package org.capstone_project.travelku.ui.presentation.screen.auth.login

import org.capstone_project.travelku.core.domain.model.AuthResultData

data class LoginState(
    val loading: Boolean = false,
    val email: String = "",
    val password: String = "",
    var loginSuccess: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val error: String? = null,
    val loginResult: AuthResultData? = null
)