package org.capstone_project.travelku.core.domain.repository

import org.capstone_project.travelku.core.data.remote.response.Role
import org.capstone_project.travelku.core.data.utils.Result
import org.capstone_project.travelku.core.domain.model.AuthResultData

interface IAuthRepository {
    suspend fun login(email: String, password: String): Result<AuthResultData>
    suspend fun register(username: String, email: String, password: String, role: Role): Result<Unit>
}