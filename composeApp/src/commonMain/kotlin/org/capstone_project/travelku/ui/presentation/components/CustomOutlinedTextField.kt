package org.capstone_project.travelku.ui.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import org.capstone_project.travelku.ui.icon.VisibilityOff
import org.capstone_project.travelku.ui.icon.VisibilityOn

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    onValueChange : (String) -> Unit,
    label: String,
    value: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    supportingText: String,
    isError: Boolean = false,
    trailingIcon: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions()
) {
    val textFieldValue = remember { mutableStateOf(TextFieldValue(value)) }
    val interactionSource = remember { MutableInteractionSource() }
    val isDoubleTap by interactionSource.collectIsDoubleTapAsState()

    LaunchedEffect(isDoubleTap) {
        val endRange = if (isDoubleTap) textFieldValue.value.text.length else 0
        textFieldValue.value =
            textFieldValue.value.copy(
                selection = TextRange(
                    start = 0,
                    end = endRange
                )
            )
    }

    OutlinedTextField(
        value = textFieldValue.value,
        onValueChange = {
            if (!isDoubleTap) {
                textFieldValue.value = it
                onValueChange(it.text)
            }
        },
        interactionSource = interactionSource,
        label = {
            Text(text = label)
        },
        shape = RoundedCornerShape(8.dp),
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        isError = isError,
        supportingText = {
            if (supportingText.isNotEmpty()) {
                Text(text = supportingText, color = MaterialTheme.colorScheme.error)
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    )
}

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    label: String,
    value: String = "",
    supportingText: String,
    isError: Boolean = false
) {
    var showPassword by remember {
        mutableStateOf(false)
    }
    CustomTextField(
        onValueChange = onValueChange,
        label = label,
        value = value,
        trailingIcon = {
            AnimatedVisibilityIcon(
                isVisible = showPassword,
                onClick = { showPassword = !showPassword }
            )
        },
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        supportingText = supportingText,
        isError = isError,
        modifier = modifier
    )
}

@Composable
fun ReadOnlyTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = {},
        enabled = false,
        label = { Text(text = label) },
        readOnly = true,
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}

@Composable
fun AnimatedVisibilityIcon(
    isVisible: Boolean,
    onClick: () -> Unit
) {
    Icon(
        imageVector = if (isVisible) VisibilityOn else VisibilityOff,
        contentDescription = if (isVisible) "Hide password" else "Show password",
        modifier = Modifier
            .clickable { onClick() }

    )
}

@Composable
fun InteractionSource.collectIsDoubleTapAsState(): State<Boolean> {
    val isDoubleTap = remember { mutableStateOf(false) }
    var firstInteractionTimeInMillis = 0L
    LaunchedEffect(this) {
        interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> {
                    val pressTimeInMillis = Clock.System.now().toEpochMilliseconds()
                    if (pressTimeInMillis - firstInteractionTimeInMillis <= 500L) {
                        firstInteractionTimeInMillis = 0
                        isDoubleTap.value = true

                    } else {
                        firstInteractionTimeInMillis = Clock.System.now().toEpochMilliseconds()
                        isDoubleTap.value = false
                    }

                }
            }

        }
    }
    return isDoubleTap
}