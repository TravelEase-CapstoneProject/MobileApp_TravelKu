package org.capstone_project.travelku.ui.presentation.model

data class User(
    val uid: String,
    val fullName: String,
    val email: String,
    val imageUrl: String,
    val phoneNumber: String,
    val isLogin: Boolean,
)
