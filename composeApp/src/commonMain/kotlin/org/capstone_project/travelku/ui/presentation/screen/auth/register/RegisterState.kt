package org.capstone_project.travelku.ui.presentation.screen.auth.register

data class RegisterState(
    val loading: Boolean = false,
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val registerSuccess: Boolean = false,
    val registerError: String? = null,
    val usernameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val registerResult: String? = null
)