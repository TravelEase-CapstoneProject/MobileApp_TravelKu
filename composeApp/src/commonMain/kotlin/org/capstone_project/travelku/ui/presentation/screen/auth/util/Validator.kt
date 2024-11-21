package org.capstone_project.travelku.ui.presentation.screen.auth.util

private val emailAddressRegex = Regex(
    "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
)

fun validateEmail(email: String): Boolean {
    return emailAddressRegex.matches(email)
}