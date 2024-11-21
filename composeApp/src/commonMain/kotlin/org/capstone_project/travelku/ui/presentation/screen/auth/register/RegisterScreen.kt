package org.capstone_project.travelku.ui.presentation.screen.auth.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import travelku.composeapp.generated.resources.registerImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit
) {

    var showDialog by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = state.registerSuccess, key2 = state.registerError) {
        if (state.registerSuccess) {
            showDialog = true
        } else if (state.registerError != null) {
            showDialog = true
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Daftar",
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
                username = state.username,
                email = state.email,
                password = state.password,
                onUsernameChanged = {
                    onEvent(RegisterEvent.OnUsernameChanged(it))
                },
                onEmailChanged = {
                    onEvent(RegisterEvent.OnEmailChanged(it))
                },
                onPasswordChanged = {
                    onEvent(RegisterEvent.OnPasswordChanged(it))
                },
                onLoginClicked = onLoginClick,
                emailError = state.emailError,
                passwordError = state.passwordError,
                onRegisterClick = {
                    onEvent(
                        RegisterEvent.OnRegisterClicked(
                            state.username,
                            state.email,
                            state.password
                        )
                    )
                },
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
                onEvent(RegisterEvent.ResetState)
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
            icon = {
            },
            title = {
                Text(
                    text = if (state.registerSuccess) "Register Berhasil" else "Terjadi Kesalahan",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )
            },
            text = {
                Text(
                    text = if (state.registerSuccess) "Kamu berhasil mendaftar, klik OK untuk melanjutkan" else state.registerError
                        ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (state.registerSuccess) {
                            onEvent(RegisterEvent.ResetState)
                            onRegisterClick()
                        } else {
                            showDialog = false
                            onEvent(RegisterEvent.ResetState)
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
    username: String,
    email: String,
    password: String,
    onUsernameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
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
                painter = painterResource(Res.drawable.registerImage),
                contentDescription = "Login Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(280.dp)
            )
            Text(
                text = "Daftar akun Anda sekarang",
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
                onValueChange = onUsernameChanged,
                label = "Username",
                isError = false,
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                supportingText = "",
                value = username,
            )
            CustomTextField(
                onValueChange = onEmailChanged,
                label = "Email",
                isError = emailError != null,
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                supportingText = emailError ?: "",
                value = email,
            )
            PasswordTextField(
                onValueChange = onPasswordChanged,
                label = "Password",
                isError = passwordError != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                supportingText = passwordError ?: "",
                value = password
            )
            ButtonUI(
                text = "Daftar",
                onClick = onRegisterClick,
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
                    text = "Sudah punya akun?",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
                TextButton(onClick = onLoginClicked) {
                    Text(text = "Login Sekarang", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
