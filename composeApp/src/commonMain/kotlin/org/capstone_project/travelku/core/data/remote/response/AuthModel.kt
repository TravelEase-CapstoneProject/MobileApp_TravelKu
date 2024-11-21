package org.capstone_project.travelku.core.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class AuthResponse(
    val status: Int,
    val message: String,
    val data: UserData? = null,
)

@Serializable
data class UserData(
    val user_id: Int,
    val email: String,
    val username: String,
    val token: String? = null,
)

@Serializable
data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val role: Role
)

@Serializable
enum class Role {
    ADMIN,
    DRIVER,
    USER
}