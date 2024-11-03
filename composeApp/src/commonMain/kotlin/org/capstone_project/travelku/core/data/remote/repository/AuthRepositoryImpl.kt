package org.capstone_project.travelku.core.data.remote.repository

import io.ktor.client.plugins.HttpRequestTimeoutException
import kotlinx.coroutines.withContext
import org.capstone_project.travelku.core.data.local.preferences.UserPreferences
import org.capstone_project.travelku.core.data.remote.response.LoginRequest
import org.capstone_project.travelku.core.data.utils.DispatcherProvider
import org.capstone_project.travelku.core.data.utils.Result
import org.capstone_project.travelku.core.domain.model.AuthResultData
import org.capstone_project.travelku.core.domain.repository.IAuthRepository

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val dispatcher: DispatcherProvider,
    private val userPreferences: UserPreferences
): IAuthRepository {
    override suspend fun login(email: String, password: String): Result<AuthResultData> {
        return withContext(dispatcher.io) {
            try {
                val request = LoginRequest(email, password)
                val authResponse = authService.login(request)

                if (authResponse.data == null) {
                    Result.Error(Exception(authResponse.message))
                } else {
                    userPreferences.saveSession(authResponse.data)
                    Result.Success(AuthResultData(
                        user_id = authResponse.data.user_id,
                        email = authResponse.data.email,
                        username = authResponse.data.username,
                        token = authResponse.data.token
                    ))
                }
            } catch (e: HttpRequestTimeoutException) {
                if (e.message == "Read timed out") {
                    Result.Error(Exception("Connection timeout"))
                } else {
                    Result.Error(Exception("Login failed"))
                }
            } catch (e: Exception) {
                Result.Error(Exception("Login failed"))
            }
        }
    }

}

