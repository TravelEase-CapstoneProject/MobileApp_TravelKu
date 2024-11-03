package org.capstone_project.travelku.ui.presentation.screen.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.capstone_project.travelku.ui.presentation.components.ButtonUI
import org.capstone_project.travelku.ui.presentation.components.CustomTextField
import org.capstone_project.travelku.ui.presentation.components.PasswordTextField
import org.jetbrains.compose.resources.painterResource
import travelku.composeapp.generated.resources.Res
import travelku.composeapp.generated.resources.loginImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    onRegisterClick: () -> Unit,
    navigateToForgetPassword: () -> Unit,
    state: LoginState,
    onEvent: (LoginEvent) -> Unit
) {

    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = state.loginSuccess, key2 = state.error) {
        if (state.loginSuccess) {
            showDialog = true
        } else if (state.error != null) {
            showDialog = true
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Login",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .imePadding()
                .padding(innerPadding)
        ) {
            LoginContent(
                email = state.email,
                password = state.password,
                onEmailChanged = {
                    onEvent(LoginEvent.OnEmailChanged(it))
                },
                onPasswordChanged = {
                    onEvent(LoginEvent.OnPasswordChanged(it))
                },
                onLoginClicked = {
                    onEvent(LoginEvent.OnLoginClicked(state.email, state.password))
//                    navigateToHome()
                },
                onForgetPasswordClick = navigateToForgetPassword,
                emailError = state.emailError,
                passwordError = state.passwordError,
                onRegisterClick = onRegisterClick,
                modifier = modifier.padding(horizontal = 16.dp)
            )
        }
    }

    if (state.loading) {
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                modifier = modifier
                    .size(100.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = MaterialTheme.shapes.large
                    ),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                onEvent(LoginEvent.ResetState)
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
            icon = {
            },
            title = {
                Text(
                    text = if (state.loginSuccess) "Login Berhasil" else "Terjadi Kesalahan",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )
            },
            text = {
                Text(
                    text = if (state.loginSuccess) "Kamu berhasil login, klik OK untuk melanjutkan" else state.error
                        ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (state.loginSuccess) {
                            navigateToHome()
                            onEvent(LoginEvent.ResetState)
                        } else {
                            showDialog = false
                            onEvent(LoginEvent.ResetState)
                        }
                    },

                    ) {
                    Text(text = "OK")
                }
            }
        )
    }
}


@Composable
fun LoginContent(
    email: String,
    password: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onForgetPasswordClick: () -> Unit,
    emailError: String?,
    passwordError: String?,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(Res.drawable.loginImage),
                contentDescription = "Login Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(280.dp)
            )
            Text(
                text = "Masukkan Username dan Password pada form berikut untuk mengakses fitur aplikasi",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .padding(horizontal = 32.dp)
            )
            CustomTextField(
                onValueChange = onEmailChanged,
                label = "Email",
                isError = emailError != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                supportingText = emailError ?: "",
                value = email,
            )
            PasswordTextField(
                onValueChange = onPasswordChanged,
                label = "Password",
                isError = passwordError != null,
                modifier = Modifier
                    .fillMaxWidth(),
                supportingText = passwordError ?: "",
                value = password
            )
            Text(
                text = "Lupa Password",
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp, bottom = 24.dp)
                    .align(Alignment.End)
                    .clickable {
                        onForgetPasswordClick()
                    },
            )
            ButtonUI(
                text = "Login",
                onClick = onLoginClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(48.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Belum punya akun?",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
                TextButton(onClick = onRegisterClick) {
                    Text(text = "Daftar Sekarang", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

