package org.capstone_project.travelku.ui.presentation.screen.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.capstone_project.travelku.core.data.utils.Result
import org.capstone_project.travelku.core.domain.usecase.AuthUseCase
import org.koin.core.component.KoinComponent

class LoginViewModel(private val useCase: AuthUseCase) : ViewModel(), KoinComponent {
    private val _state = MutableStateFlow(LoginState())
    val state get() = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.OnEmailChanged -> {
                _state.update {
                    val isValid = validateEmail(event.email)
                    it.copy(
                        email = event.email,
                        emailError = if (isValid) null else "Masukkan email yang valid!"
                    )
                }
            }
            is LoginEvent.OnLoginClicked -> {
                val state = _state.value
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
                login(state.email, state.password)
            }
            is LoginEvent.OnPasswordChanged -> {
                _state.update {
                    it.copy(
                        password = event.password,
                        passwordError = if (event.password.isBlank()) "Password cannot be empty!" else null
                    )
                }
            }
            is LoginEvent.ResetState -> {
                _state.update {
                    LoginState(
                        email = it.email,
                        password = "",
                        emailError = null,
                        passwordError = null,
                        loginSuccess = false,
                        loginResult = null
                    )
                }
            }
            is LoginEvent.SetLoadingState -> {
                _state.update {
                    it.copy(loading = true)
                }
            }
        }
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(loading = true)
            }
            val result = useCase.login(email, password)
            when(result) {
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            loginSuccess = true
                        )
                    }
                }
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            error = "Login failed!"
                        )
                    }
                }

                Result.Loading -> {
                    _state.update {
                        it.copy(loading = true)
                    }
                }
            }
        }
    }
}

private val emailAddressRegex = Regex(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)

private fun validateEmail(email: String): Boolean {
    return emailAddressRegex.matches(email)
}