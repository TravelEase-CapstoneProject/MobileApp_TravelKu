package org.capstone_project.travelku.core.domain.model

data class AuthResultData(
    val user_id: Int,
    val email: String,
    val username: String,
    val token: String,
)
