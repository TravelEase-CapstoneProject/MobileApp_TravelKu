package org.capstone_project.travelku.ui.presentation.screen.auth.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel() : ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state get() = _state.asStateFlow()

    fun onEvent(event: RegisterEvent) {
        when(event) {
            is RegisterEvent.OnEmailChanged -> {
                _state.update {
                    val isValid = validateEmail(event.email)
                    it.copy(
                        email = event.email,
                        emailError = if (isValid) null else "Invalid email!"
                    )
                }
            }
            is RegisterEvent.OnLoginClicked -> TODO()
            is RegisterEvent.OnPasswordChanged -> {
                _state.update {
                    it.copy(
                        password = event.password,
                        passwordError = if (event.password.isBlank()) "Password cannot be empty!" else null
                    )
                }
            }
            is RegisterEvent.ResetState -> {
                _state.update {
                    RegisterState(
                        email = it.email,
                        password = "",
                        emailError = null,
                        passwordError = null,
                        loginError = null,
                        loginSuccess = false,
                        loginResult = null
                    )
                }
            }
            is RegisterEvent.SetLoadingState -> TODO()
        }
    }
}

private val emailAddressRegex = Regex(
    "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
)

fun validateEmail(email: String): Boolean {
    return emailAddressRegex.matches(email)
}