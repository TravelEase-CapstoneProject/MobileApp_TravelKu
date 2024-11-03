package org.capstone_project.travelku.ui.presentation.screen.auth.register

data class RegisterState(
    val loading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val loginSuccess: Boolean = false,
    val loginError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val loginResult: String? = null
)