package org.capstone_project.travelku.ui.presentation.screen.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.capstone_project.travelku.core.data.remote.response.Role
import org.capstone_project.travelku.core.data.utils.Result
import org.capstone_project.travelku.core.domain.usecase.AuthUseCase
import org.capstone_project.travelku.ui.presentation.screen.auth.util.validateEmail
import org.koin.core.component.KoinComponent

class RegisterViewModel(private val useCase: AuthUseCase) : ViewModel(), KoinComponent {
    private val _state = MutableStateFlow(RegisterState())
    val state get() = _state.asStateFlow()

    fun onEvent(event: RegisterEvent) {
        when(event) {
            is RegisterEvent.OnUsernameChanged -> {
                _state.update {
                    it.copy(username = event.username)
                }
            }
            is RegisterEvent.OnEmailChanged -> {
                _state.update {
                    val isValid = validateEmail(event.email)
                    it.copy(
                        email = event.email,
                        emailError = if (isValid) null else "Masukkan email yang valid!"
                    )
                }
            }
            is RegisterEvent.OnPasswordChanged -> {
                _state.update {
                    it.copy(
                        password = event.password,
                        passwordError = if (event.password.isBlank()) "Password cannot be empty!" else null
                    )
                }
            }
            is RegisterEvent.OnRegisterClicked -> {
                val state = _state.value
                if (state.username.isBlank()) {
                    _state.update {
                        it.copy(usernameError = "Username cannot be empty!")
                    }
                    return
                }
                if (state.email.isBlank()) {
                    _state.update {
                        it.copy(emailError = "Email cannot be empty!")
                    }
                    return
                }
                if (state.password.isBlank()) {
                    _state.update {
                        it.copy(passwordError = "Password cannot be empty!")
                    }
                    return
                }
                register(event.username, event.email, event.password)
            }
            is RegisterEvent.ResetState -> {
                _state.update {
                    RegisterState()
                }
            }
            is RegisterEvent.SetLoadingState -> {
                _state.update {
                    it.copy(loading = event.isLoading)
                }
            }
        }
    }

    private fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            _state.update { it.copy(loading = true) }
            val result = useCase.register(username, email, password, Role.USER)
            when(result) {
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            registerSuccess = true
                        )
                    }
                }
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            registerError = result.exception.message
                        )
                    }
                }
                Result.Loading -> {
                    _state.update { it.copy(loading = true) }
                }
            }
        }
    }
}