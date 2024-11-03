package org.capstone_project.travelku.core.data.remote.repository

import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.forms.submitForm
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.capstone_project.travelku.core.data.remote.BASE_URL
import org.capstone_project.travelku.core.data.remote.KtorApi
import org.capstone_project.travelku.core.data.remote.response.AuthResponse
import org.capstone_project.travelku.core.data.remote.response.LoginRequest

class AuthService: KtorApi() {
    suspend fun login(request: LoginRequest): AuthResponse {
        return try {
            client.submitForm(
                url = "${BASE_URL}users/login",
                formParameters = Parameters.build {
                    append("email", request.email)
                    append("password", request.password)
                }
            ).body()
        } catch (e: ResponseException) {
            val errorResponse = e.response.bodyAsText()
            val errorMessage = Json.parseToJsonElement(errorResponse).jsonObject["message"]?.jsonPrimitive?.content
            when (e.response.status) {
                HttpStatusCode.Unauthorized -> AuthResponse(status = 401, message = errorMessage ?: "Unauthorized")
                HttpStatusCode.InternalServerError -> AuthResponse(status = 500, message = errorMessage ?: "Internal server error")
                else -> throw e
            }
        }
    }
}